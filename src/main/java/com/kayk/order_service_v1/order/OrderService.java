package com.kayk.order_service_v1.order;

import com.kayk.order_service_v1.order.dtos.OrderEventDTO;
import com.kayk.order_service_v1.order.dtos.OrderItemDTO;
import com.kayk.order_service_v1.order.dtos.OrderRequestDTO;
import com.kayk.order_service_v1.order.dtos.OrderResponseDTO;
import com.kayk.order_service_v1.order.mapper.OrderMapper;
import com.kayk.order_service_v1.order.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderProducer producer;

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAll() {
        return repository.findAll().stream().map(orders -> mapper.toDto(orders)).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO findById(UUID id) {
        return repository.findById(id).stream().map(orders -> mapper.toDto(orders)).findFirst().orElseThrow(() -> new RuntimeException("Resource not found" + id));
    }

    @Transactional
    public OrderResponseDTO insert(OrderRequestDTO dto) {
        Order entity = mapper.toEntity(dto);

        for(Orderitem value : dto.orderItems()) {
            entity.addItem(value);
            value.setOrder(entity);
        }
        repository.save(entity);

        OrderEventDTO eventDTO = new OrderEventDTO(entity.getId(), entity.getClient(),dto.email(), dto.orderItems(), entity.getTotalValue(), entity.getCreatedAt());
        producer.publishEvent("order-topic-v2", eventDTO);

        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
