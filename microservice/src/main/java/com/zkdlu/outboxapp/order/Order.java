package com.zkdlu.outboxapp.order;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ORDERS")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    protected Order() {
    }

    public Order(long orderId) {
        this.id = orderId;
        this.state = OrderState.PREPARE;
    }

    public Long getId() {
        return id;
    }

    public OrderState getState() {
        return state;
    }

    public void complete() {
        this.state = OrderState.COMPLETE;
    }
}
