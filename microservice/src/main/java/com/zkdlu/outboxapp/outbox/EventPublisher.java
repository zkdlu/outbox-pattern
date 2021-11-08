package com.zkdlu.outboxapp.outbox;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(OutboxEvent outboxEvent) {
        publisher.publishEvent(outboxEvent);
    }
}
