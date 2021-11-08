package com.zkdlu.outboxapp.order;


import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    void newOrder(long orderId) throws JsonProcessingException;

    void completeOrder(long orderId) throws JsonProcessingException;

    Order getOrder(long orderId);
}
