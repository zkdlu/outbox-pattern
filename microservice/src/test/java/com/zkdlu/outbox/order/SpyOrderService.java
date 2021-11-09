package com.zkdlu.outbox.order;

import com.zkdlu.outboxapp.order.Order;
import com.zkdlu.outboxapp.order.OrderService;

public class SpyOrderService implements OrderService {
    public long newOrder_argumentOrderId;
    public long completeOrder_argumentOrderId;
    public long getOrder_argumentOrderId;
    public Order getOrder_returnValue;
    public boolean newOrder_wasCalled;

    @Override
    public void newOrder() {
        newOrder_wasCalled = true;
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
