package com.zkdlu.outboxapp.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApi {
    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity createOrder() throws JsonProcessingException {
        orderService.newOrder();
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping("/order/{orderId}")
    public ResponseEntity completeOrder(@PathVariable long orderId) throws JsonProcessingException {
        orderService.completeOrder(orderId);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity getOrder(@PathVariable long orderId) {
        return ResponseEntity.ok()
                .body(orderService.getOrder(orderId));
    }
}
