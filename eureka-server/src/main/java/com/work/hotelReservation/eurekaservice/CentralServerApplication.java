package com.work.hotelReservation.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CentralServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralServerApplication.class, args);
	}

}
