package com.example.demo;

import javax.persistence.*;

@Entity
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateId;

    private String eventType;

    @Lob
    private String payload;

    private String status;

    public OutboxEvent() {
    }

    public OutboxEvent(
            String aggregateId,
            String eventType,
            String payload,
            String status
    ) {
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}