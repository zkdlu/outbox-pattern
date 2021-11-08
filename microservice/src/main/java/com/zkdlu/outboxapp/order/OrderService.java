package com.zkdlu.outboxapp.order;


public interface OrderService {
    void newOrder(long orderId);

    void completeOrder(long orderId);

    Order getOrder(long orderId);
}
