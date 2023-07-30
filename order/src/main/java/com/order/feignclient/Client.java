package com.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service", url = "http://localhost:9030/")
public interface Client {

    @GetMapping(value = "/api/v1/client/{id}")
    public Object getClientId(@PathVariable(value = "id") Long clientId);

}