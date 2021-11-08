package com.zkdlu.outbox.order;

import com.zkdlu.outboxapp.order.Order;
import com.zkdlu.outboxapp.order.OrderService;

public class SpyOrderService implements OrderService {
    public long newOrder_argumentOrderId;
    public long completeOrder_argumentOrderId;
    public long getOrder_argumentOrderId;
    public Order getOrder_returnValue;

    @Override
    public void newOrder(long orderId) {
        newOrder_argumentOrderId = orderId;
    }

    @Override
    public void completeOrder(long orderId) {
        completeOrder_argumentOrderId = orderId;
    }

    @Override
    public Order getOrder(long orderId) {
        getOrder_argumentOrderId = orderId;
        return getOrder_returnValue;
    }
}
