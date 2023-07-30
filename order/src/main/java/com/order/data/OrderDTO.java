package com.order.data;

import com.order.entities.OrderItem;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class OrderDTO {

    @NotNull
    private Long customerId;
    @NotNull
    private LocalDate orderDate;

    @NotNull
    private String shippingAddress;
    @NotNull
    private String billingAddress;
    @NotNull
    private Double totalAmount;
    @NotNull
    private String paymentMethod;
    @Valid
    @NotNull
    private List<OrderItemDTO> items;
}
