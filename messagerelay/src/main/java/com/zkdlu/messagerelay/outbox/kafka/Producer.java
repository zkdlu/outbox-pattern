package com.zkdlu.messagerelay.outbox.kafka;

import com.zkdlu.messagerelay.outbox.Outbox;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final KafkaTemplate<String, Outbox> kafkaTemplate;

    public Producer(KafkaTemplate<String, Outbox> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(NewTopic topic, Outbox message) {
        kafkaTemplate.send(topic.name(), message);
    }
}
