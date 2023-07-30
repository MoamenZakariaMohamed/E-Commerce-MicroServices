package com.order.controllers;


import com.order.data.OrderDTO;
import com.order.entities.Order;
import com.order.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/mostRequested")
    public Object getMostRequestedProduct() {
        return orderService.getAllOrdersItems();
    }

    @PostMapping("")
    public Order createOrder(@RequestBody OrderDTO product) {
        return orderService.createOrder(product);
    }


}
