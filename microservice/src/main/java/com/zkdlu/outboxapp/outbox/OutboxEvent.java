package com.zkdlu.outboxapp.outbox;

import java.time.LocalDateTime;

public class OutboxEvent {
    private String aggregateId;
    private String aggregateType;
    private String payload;
    private String eventType;
    private LocalDateTime createAt;

    public OutboxEvent(String aggregateId, String aggregateType, String payload, String eventType, LocalDateTime createAt) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.payload = payload;
        this.eventType = eventType;
        this.createAt = createAt;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getPayload() {
        return payload;
    }

    public String getEventType() {
        return eventType;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
