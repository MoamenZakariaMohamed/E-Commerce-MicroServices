package com.example.gatway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r
                        .path("/products/**")
                        .uri("http://localhost:9010/products")
                )
                .route("order-service", r -> r
                        .path("/orders/**")
                        .uri("http://localhost:9040/orders")
                )
                .route("client-service", r -> r
                        .path("/client/**")
                        .uri("http://localhost:9030/client/")
                )
                .build();
    }


}
