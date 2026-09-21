package com.kayk.order_service_v1.order.mapper;

import com.kayk.order_service_v1.order.Order;
import com.kayk.order_service_v1.order.dtos.OrderRequestDTO;
import com.kayk.order_service_v1.order.dtos.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderRequestDTO dto);
    OrderResponseDTO toDto(Order entity);
}
