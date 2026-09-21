package com.kayk.order_service_v1.order.dtos;

import com.kayk.order_service_v1.order.Orderitem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderEventDTO(
        UUID id,
        String client,
        String email,
        List<Orderitem> orderItem,
        BigDecimal totalValue,
        LocalDateTime createdAt
) {
}
