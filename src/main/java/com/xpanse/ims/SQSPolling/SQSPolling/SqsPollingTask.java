package com.xpanse.ims.SQSPolling.SQSPolling;

import com.xpanse.ims.SQSPolling.SQSService.SqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SqsPollingTask {

    private static final Logger logger = LoggerFactory.getLogger(SqsPollingTask.class);
    private final SqsService sqsService;

    public SqsPollingTask(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    // Poll every 3 seconds
    @Scheduled(fixedRate = 3000)
    public void pollMessages() {
        logger.info("Polling for messages from SQS...");
        sqsService.pullMessages();
        logger.info("Polling completed.");
    }
}
