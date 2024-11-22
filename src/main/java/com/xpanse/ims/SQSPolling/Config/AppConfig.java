package com.xpanse.ims.SQSPolling.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public RestTemplate restTemplate() {
        logger.info("Creating RestTemplate bean");
        return new RestTemplate();
    }

    @Bean
    public SqsClient sqsClient() {
        logger.info("Creating SqsClient bean");
        return SqsClient.builder()
                .region(Region.of("us-east-1"))
                .credentialsProvider(ProfileCredentialsProvider.builder()
                        .profileName("Default")
                        .build()) // Use default profile from ~/.aws/credentials
                .build();
    }
}
