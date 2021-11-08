package com.zkdlu.outboxapp.order.outbox;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventSubscriber {
    private final OutboxRepository outboxRepository;

    public EventSubscriber(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @EventListener
    public void handleOutboxEvent(OutboxEvent outboxEvent) {
        Outbox outbox = new Outbox(
                outboxEvent.getAggregateId(),
                outboxEvent.getAggregateType(),
                outboxEvent.getPayload(),
                outboxEvent.getEventType(),
                outboxEvent.getCreateAt()
        );

        outboxRepository.save(outbox);

        throw new RuntimeException();
    }
}
