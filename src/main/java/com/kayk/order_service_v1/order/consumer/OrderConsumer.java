package com.kayk.order_service_v1.order.consumer;

import com.kayk.order_service_v1.order.dtos.OrderEventDTO;
import com.kayk.order_service_v1.order.service.AwsService;
import com.kayk.order_service_v1.order.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    @Autowired
    private AwsService awsService;

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "order-topic-v2", groupId = "order-service-group-id")
    public void orderConsumer(OrderEventDTO dto) {
        try {
            System.out.println("-------NEW ORDER -------");
            awsService.uploadS3(dto);
            emailService.emailSender(dto);
        }catch (Exception e) {
            System.out.println("FAIL WHILE UPLOAD FILE");
        }
    }
}
