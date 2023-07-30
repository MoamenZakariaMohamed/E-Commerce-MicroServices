package com.order.data;

public enum OrderStatusEnum {
    ORDERED("Ordered"),
    READY("Ready"),
    DELIVERED("Delivered");

    private final String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
