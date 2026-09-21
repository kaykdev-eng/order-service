package com.kayk.order_service_v1.order;

import com.kayk.order_service_v1.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String client;
    private String email;
    private BigDecimal totalValue;
    private OrderStatus status;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    private List<Orderitem> orderItems = new ArrayList<>();

    public Order(String client, String email, LocalDateTime createdAt) {
        this.client = client;
        this.email = email;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if(this.status == null){
            this.status = OrderStatus.ORDER_RECEIVED;
        }

        if(this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public void addItem(Orderitem item) {
        orderItems.add(item);
        item.setOrder(this);

        if(this.totalValue == null) {
            this.totalValue = BigDecimal.ZERO;
        }

        BigDecimal subTotal = item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
        this.totalValue = totalValue.add(subTotal);
    }
}
