package com.order.entities;

import com.order.data.OrderDTO;
import com.order.data.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    private Long customer_id;

    private LocalDate orderDate;

    private String orderStatus;

    private String shippingAddress;

    private Double totalAmount;

    private String paymentMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> items;



    public static Order createOrder(OrderDTO orderDTO,List<OrderItem> orderItems) {
        return Order.builder()
                .customer_id(orderDTO.getCustomerId())
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatusEnum.ORDERED.getValue())
                .shippingAddress(orderDTO.getShippingAddress())
                .totalAmount(orderDTO.getTotalAmount())
                .paymentMethod(orderDTO.getPaymentMethod())
                .items(orderItems)
                .build();
    }

}
