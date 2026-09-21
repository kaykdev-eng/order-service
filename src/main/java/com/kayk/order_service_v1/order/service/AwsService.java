package com.kayk.order_service_v1.order.service;

import com.kayk.order_service_v1.order.dtos.OrderEventDTO;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class AwsService {

    private S3Template s3Template;
    private ObjectMapper objectMapper;
    @Value("${aws.s3.bucket.name}")
    private String bucket;

    public AwsService(S3Template s3Template, ObjectMapper objectMapper) {
        this.s3Template = s3Template;
        this.objectMapper = objectMapper;
    }

    public void uploadS3(OrderEventDTO dto) {
        try {
            String JSON = objectMapper.writeValueAsString(dto);
            String file = "order-" + dto.id() + ".json";

            InputStream inputStream = new ByteArrayInputStream(JSON.getBytes(StandardCharsets.UTF_8));
            System.out.println("Starting DTO upload to S3: "+ file);

            s3Template.upload(bucket, file, inputStream);
            System.out.println(" Upload completed successfully! File: "+ file);
        } catch (Exception e) {
            System.out.println("Error while attempting to convert and save to S3: " + e.getMessage());
        }
    }
}
