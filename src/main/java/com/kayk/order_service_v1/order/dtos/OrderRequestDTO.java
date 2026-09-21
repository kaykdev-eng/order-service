package com.kayk.order_service_v1.order.dtos;

import com.kayk.order_service_v1.order.Orderitem;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequestDTO(
        @NotBlank(message = "Client required")
        String client,
        @NotBlank
        @Email(message = "Email required")
        String email,
        @NotEmpty
        List<Orderitem> orderItems
) {
}
