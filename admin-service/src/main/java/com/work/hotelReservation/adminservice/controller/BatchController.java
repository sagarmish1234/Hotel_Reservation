package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.config.BatchConfig;
import com.work.hotelReservation.adminservice.utils.ApiUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("hotelJob")
    private Job hotelJob;

    @Autowired
    @Qualifier("discountJob")
    private Job discountJob;

    @Autowired
    @Qualifier("voucherJob")
    private Job voucherJob;

    @Autowired
    @Qualifier("roomJob")
    private Job roomJob;




    @GetMapping("/startHotelJob")
    public ResponseEntity<?> startHotelJob() {
        try {
            JobParameters jobParameters = BatchConfig.getJobParameters(ApiUtil.HOTEL_JOB_STRING);
            jobLauncher.run(hotelJob, jobParameters);
            return ResponseEntity.ok(ApiUtil.generateResponse("message", "Hotel Job started"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message", e.getMessage()));
        }
    }

    @GetMapping("/startDiscountJob")
    public ResponseEntity<?> startDiscountJob() {
        try {
            JobParameters jobParameters = BatchConfig.getJobParameters(ApiUtil.DISCOUNT_JOB_STRING);
            jobLauncher.run(discountJob, jobParameters);
            return ResponseEntity.ok(ApiUtil.generateResponse("message", "Discount Job started"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message", e.getMessage()));
        }
    }

    @GetMapping("/startVoucherJob")
    public ResponseEntity<?> startVoucherJob() {
        try {
            JobParameters jobParameters = BatchConfig.getJobParameters(ApiUtil.VOUCHER_JOB_STRING);
            jobLauncher.run(voucherJob, jobParameters);
            return ResponseEntity.ok(ApiUtil.generateResponse("message", "Voucher Job started"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message", e.getMessage()));
        }
    }

    @GetMapping("/startRoomJob")
    public ResponseEntity<?> startRoomJob() {
        try {
            JobParameters jobParameters = BatchConfig.getJobParameters(ApiUtil.ROOM_JOB_STRING);
            jobLauncher.run(roomJob, jobParameters);
            return ResponseEntity.ok(ApiUtil.generateResponse("message", "Room Job started"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message", e.getMessage()));
        }
    }

}
