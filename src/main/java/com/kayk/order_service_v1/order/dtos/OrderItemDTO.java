package com.kayk.order_service_v1.order.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(
        UUID id,
        String product,
        Integer quantity,
        BigDecimal unitPrice
) {
}
