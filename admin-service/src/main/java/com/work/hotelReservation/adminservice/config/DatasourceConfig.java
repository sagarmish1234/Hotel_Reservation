package com.work.hotelReservation.adminservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("batchDatasource")
    @ConfigurationProperties(prefix = "spring.batchdatasource")
    public DataSource batchDataSource(){
        return DataSourceBuilder.create().build();
    }


}
