package com.work.hotelReservation.adminservice.config;

import com.work.hotelReservation.adminservice.model.Discount;
import com.work.hotelReservation.adminservice.model.Hotel;
import com.work.hotelReservation.adminservice.utils.ApiUtil;
import com.work.hotelReservation.adminservice.utils.DbUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfiguration {

    @Autowired
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("batchDatasource")
    private DataSource batchDataSource;


    public static JobParameters getJobParameters(String jobContextName) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date());
        jobParametersBuilder.addString("name", jobContextName);

        return jobParametersBuilder.toJobParameters();
    }
    @Bean
    public ConversionService testConversionService() {
        DefaultConversionService testConversionService = new DefaultConversionService();
        DefaultConversionService.addDefaultConverters(testConversionService);
        testConversionService.addConverter(new Converter<String, LocalDate>(){

            @Override
            public LocalDate convert(String source) {
                return LocalDate.parse(source, DateTimeFormatter.ISO_DATE_TIME);
            }
        });

        return testConversionService;
    }
    //Jobs
    @Bean("hotelJob")
    public Job hotelJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new JobBuilder("HotelJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(hotelStep(jobRepository,transactionManager))
                .build();
    }

    @Bean("discountJob")
    public Job discountJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new JobBuilder("DiscountJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(discountStep(jobRepository,transactionManager))
                .build();
    }


    //Steps
    @Bean("hotelStep")
    public Step hotelStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("HotelStep", jobRepository)
                .<Hotel,Hotel>chunk(100, transactionManager) // or
                .reader(hotelFlatFileItemReader())
                .writer(hotelWriter())
                .build();
    }

    @Bean("discountStep")
    public Step discountStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("DiscountStep", jobRepository)
                .<Discount,Discount>chunk(100, transactionManager) // or
                .reader(discountFlatFileItemReader())
                .writer(discountWriter())
                .build();
    }


    //Readers

    @Bean
    public FlatFileItemReader<Hotel> hotelFlatFileItemReader(){
        Resource resource =new FileSystemResource(new File(ApiUtil.ABSOLUTE_PATH + "hotel.csv"));
        FlatFileItemReader<Hotel> flatFileItemReader = new FlatFileItemReader<Hotel>();
        flatFileItemReader.setResource((resource));
        flatFileItemReader.setLineMapper(new DefaultLineMapper<Hotel>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames("ID","Name","Address","Phone","City");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Hotel>(){
                    {
                        setTargetType(Hotel.class);
                    }
                });
            }
        });
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

    @Bean
    public FlatFileItemReader<Discount> discountFlatFileItemReader(){
        Resource resource =new FileSystemResource(new File(ApiUtil.ABSOLUTE_PATH + "discount.csv"));
        FlatFileItemReader<Discount> flatFileItemReader = new FlatFileItemReader<Discount>();
        flatFileItemReader.setResource((resource));
        flatFileItemReader.setLineMapper(new DefaultLineMapper<Discount>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames("ID","Name","Applicable From","Applicable Till","Percentage");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Discount>(){
                    {
                        setTargetType(Discount.class);
                        setConversionService(testConversionService());
                    }
                });
            }
        });
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

    //Writer
    @Bean("hotelWriter")
    public JdbcBatchItemWriter<Hotel> hotelWriter() {
        JdbcBatchItemWriter<Hotel> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(primaryDataSource);
        writer.setSql(DbUtil.HOTEL_QUERY);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

    @Bean("discountWriter")
    public JdbcBatchItemWriter<Discount> discountWriter() {
        JdbcBatchItemWriter<Discount> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(primaryDataSource);
        writer.setSql(DbUtil.DISCOUNT_QUERY);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }


    @Override
    protected DataSource getDataSource() {
        return batchDataSource;
    }
}
