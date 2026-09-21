package com.kayk.order_service_v1.order.enums;

public enum OrderStatus {
    ORDER_RECEIVED("ORDER_RECEIVED"),
    ORDER_ACCEPTED("ORDER_ACCEPTED"),
    PAYMENT_SUCESS("PAYMENT_SUCESS"),
    ORDER_CANCELED("ORDER_CANCELED");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getstatus() {
        return status;
    }
}
