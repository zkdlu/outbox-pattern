package com.zkdlu.outbox.order;

import com.zkdlu.outboxapp.order.Order;
import com.zkdlu.outboxapp.order.OrderService;
import com.zkdlu.outboxapp.order.OrderServiceImpl;
import com.zkdlu.outboxapp.order.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private OrderService orderService;
    private SpyOrderRepository spyOrderRepository;

    @BeforeEach
    void setUp() {
        spyOrderRepository = new SpyOrderRepository();
        orderService = new OrderServiceImpl(spyOrderRepository);
    }

    @Test
    void newOrder_callsSaveInOrderRepository() {
        long givenOrderId = 1L;

        orderService.newOrder(givenOrderId);

        Order savedOrder = spyOrderRepository.save_argument;

        assertThat(savedOrder.getId()).isEqualTo(givenOrderId);
    }

    @Test
    void completeOrder_callsFindByIdInOrderRepository() {
        long givenOrderId = 1L;
        Order givenOrder = new Order(givenOrderId);
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        orderService.completeOrder(givenOrderId);

        assertThat(spyOrderRepository.findById_argument).isEqualTo(givenOrderId);
    }

    @Test
    void completeOrder_callsCompleteInOrder() {
        long givenOrderId = 1L;

        Order givenOrder = new Order(givenOrderId);
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        orderService.completeOrder(givenOrderId);

        assertThat(givenOrder.getState()).isEqualTo(OrderState.COMPLETE);
    }


    @Test
    void getOrder_callsFindByIdInOrderRepository() {
        long givenOrderId = 1L;
        Order givenOrder = new Order(givenOrderId);
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        orderService.getOrder(givenOrderId);

        assertThat(spyOrderRepository.findById_argument).isEqualTo(givenOrderId);
    }

    @Test
    void getOrder_returnsOrder() {
        long givenOrderId = 1L;
        Order givenOrder = new Order(givenOrderId);
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        Order result = orderService.getOrder(givenOrderId);

        assertThat(result).isEqualTo(givenOrder);
    }
}