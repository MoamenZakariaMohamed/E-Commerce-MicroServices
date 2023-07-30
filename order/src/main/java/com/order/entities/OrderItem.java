package com.order.entities;

import com.order.data.OrderDTO;
import com.order.data.OrderItemDTO;
import com.order.data.OrderStatusEnum;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long product_id;

    private Integer quantity;

    private Double price;

    public static OrderItem createOrderItem(OrderItemDTO orderItems) {
        return OrderItem.builder()
                .product_id(orderItems.getProduct_id())
                .quantity(orderItems.getQuantity())
                .price(orderItems.getPrice())
                .build();
    }

}
