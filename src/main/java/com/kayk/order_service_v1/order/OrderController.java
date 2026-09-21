package com.kayk.order_service_v1.order;

import com.kayk.order_service_v1.order.dtos.OrderRequestDTO;
import com.kayk.order_service_v1.order.dtos.OrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<OrderResponseDTO> allOrders = service.findAll();
        return ResponseEntity.ok().body(allOrders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id) {
        OrderResponseDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@Valid @RequestBody OrderRequestDTO dto) {
        OrderResponseDTO newDto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable  UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
