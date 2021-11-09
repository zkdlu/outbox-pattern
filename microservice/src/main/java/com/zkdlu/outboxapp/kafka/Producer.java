package com.zkdlu.outboxapp.kafka;

import com.zkdlu.outboxapp.outbox.Outbox;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Producer {
    private final KafkaTemplate<String, Outbox> kafkaTemplate;

    public Producer(KafkaTemplate<String, Outbox> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, Outbox>> sendMessage(NewTopic topic, String partitionKey, Outbox message) {
        return kafkaTemplate.send(topic.name(), partitionKey,  message);
    }
}
