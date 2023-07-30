package com.order.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class OrderItemDTO {
    @NotNull
    private Long product_id;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double price;

}
