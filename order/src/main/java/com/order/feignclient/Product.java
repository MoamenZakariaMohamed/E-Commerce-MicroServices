package com.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:9010/")

public interface Product {
    @GetMapping(value = "/products/{id}")
    public Object getProductId(@PathVariable(value = "id") Long productId);

    @GetMapping(value = "/products/ids")
    public Object getProductsByIds(@RequestParam List<Long> ids);

}
