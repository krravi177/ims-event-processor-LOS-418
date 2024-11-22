package com.xpanse.ims.SQSPolling.SQSService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Service
public class SqsService {

    private static final Logger logger = LoggerFactory.getLogger(SqsService.class);
    private final SqsClient sqsClient;

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    // Constructor injection of the SQS Client
    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    // Method to pull messages from SQS
    public void pullMessages() {
        // Build the request to receive messages from the SQS queue
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)  // Queue URL injected via @Value
                .maxNumberOfMessages(10)
                .waitTimeSeconds(20)
                .build();

        // Receive messages
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

        // Process each message
        for (Message message : messages) {
            // Print the message body to the console
            logger.info("Message received: {}", message.body());

            // Optionally delete the message from the queue after processing
            deleteMessage(message);
        }
    }

    // Method to delete the message after processing it
    private void deleteMessage(Message message) {
        // Delete the message from the SQS queue
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)  // Use the injected queue URL
                .receiptHandle(message.receiptHandle())
                .build();
        sqsClient.deleteMessage(deleteMessageRequest);

        // Log the message deletion
        logger.info("Message deleted: {}", message.messageId());
    }
}
