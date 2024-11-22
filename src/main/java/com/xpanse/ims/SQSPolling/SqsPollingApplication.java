package com.xpanse.ims.SQSPolling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SqsPollingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsPollingApplication.class, args);
	}

}
