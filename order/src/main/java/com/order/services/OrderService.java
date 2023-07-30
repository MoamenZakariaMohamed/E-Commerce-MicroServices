package com.order.services;


import com.order.data.OrderDTO;
import com.order.data.OrderItemDTO;
import com.order.entities.Order;
import com.order.entities.OrderItem;
import com.order.exceptions.NotFoundException;
import com.order.feignclient.Client;
import com.order.feignclient.Product;
import com.order.repositories.OrderItemRepository;
import com.order.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Client clientFeign;
    private final Product productFeign;
    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }


    public Object getAllOrdersItems() {
        List<OrderItem> orderItemList= orderItemRepository.findAll();
        List<Long> productIds = orderItemList.stream()
                .map(orderItem -> orderItem.getProduct_id())
                .collect(Collectors.toList());
        Object product = productFeign.getProductsByIds(productIds);
        return product;
    }

    public Order getOrderById(Long id) {
        
        return orderRepository.findById(id).orElse(null);
    }

    public void getProductById(Long id) {
       Object product = productFeign.getProductId(id);
        if (product == null) {
            throw new NotFoundException("Product with ID " + id + " not found.");
        }
    }



    public Order createOrder(OrderDTO orderDTO) {

        Object client=clientFeign.getClientId(orderDTO.getCustomerId());
        if (client == null) {
            throw new NotFoundException("Client with ID " + orderDTO.getCustomerId() + " not found.");
        }

        List<OrderItem> orderItemsList=new ArrayList<>();
        for (OrderItemDTO item : orderDTO.getItems()) {
             getProductById(item.getProduct_id());
             OrderItem orderItem=OrderItem.createOrderItem(item);
             orderItemsList.add(orderItem);
        }

        Order order=Order.createOrder(orderDTO,orderItemsList);
       return  orderRepository.save(order);
    }
}
