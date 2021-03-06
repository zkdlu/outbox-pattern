package com.zkdlu.outboxapp.outbox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "OUTBOX")
@Entity
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String aggregateId;
    private String aggregateType;
    private String payload;
    private String eventType;
    private LocalDateTime createAt;

    protected Outbox() {
    }

    public Outbox(String aggregateId, String aggregateType, String payload, String eventType, LocalDateTime createAt) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.payload = payload;
        this.eventType = eventType;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
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

