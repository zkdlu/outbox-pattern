package com.zkdlu.outboxapp.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public void newOrder(long orderId) {
        Order order = new Order(orderId);

        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void completeOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .get();

        order.complete();
    }

    @Override
    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId).get();
    }
}
