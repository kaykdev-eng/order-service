package com.kayk.order_service_v1.order.producer;

import com.kayk.order_service_v1.order.dtos.OrderEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderEventDTO> kafkaTemplate;

    public void publishEvent(String topic, OrderEventDTO dto) {
        kafkaTemplate.send(topic, dto);
    }

}
