package com.zkdlu.outboxapp.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkdlu.outboxapp.order.outbox.EventPublisher;
import com.zkdlu.outboxapp.order.outbox.OutboxEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;
    private final ObjectMapper objectMapper;

    public OrderServiceImpl(OrderRepository orderRepository, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    @Override
    public void newOrder(long orderId) throws JsonProcessingException {
        Order order = new Order(orderId);

        orderRepository.save(order);

        eventPublisher.publish(new OutboxEvent(
                order.getId().toString(),
                Order.class.getTypeName(),
                objectMapper.writeValueAsString(order),
                "CREATE",
                LocalDateTime.now()));
    }

    @Transactional
    @Override
    public void completeOrder(long orderId) throws JsonProcessingException {
        Order order = orderRepository.findById(orderId)
                .get();

        order.complete();

        eventPublisher.publish(new OutboxEvent(
                order.getId().toString(),
                Order.class.getTypeName(),
                objectMapper.writeValueAsString(order),
                "COMPLETE",
                LocalDateTime.now()));
    }

    @Override
    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId).get();
    }
}
