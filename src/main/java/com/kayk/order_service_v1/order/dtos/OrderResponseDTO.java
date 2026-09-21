package com.kayk.order_service_v1.order.dtos;

import com.kayk.order_service_v1.order.Order;
import com.kayk.order_service_v1.order.Orderitem;
import com.kayk.order_service_v1.order.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        String client,
        String email,
        List<Orderitem> orderItems,
        BigDecimal totalValue,
        OrderStatus status,
        LocalDateTime createdAt
) {
}
