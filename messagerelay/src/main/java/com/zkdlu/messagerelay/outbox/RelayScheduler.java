package com.zkdlu.messagerelay.outbox;

import com.zkdlu.messagerelay.outbox.kafka.Producer;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableScheduling
@Component
public class RelayScheduler {
    private final Logger log = LoggerFactory.getLogger(RelayScheduler.class);
    private final OutboxRepository outboxRepository;
    private final Producer producer;

    public RelayScheduler(OutboxRepository outboxRepository, Producer producer) {
        this.outboxRepository = outboxRepository;
        this.producer = producer;
    }

    @Scheduled(cron = "* * * * * *")
    public void relayFromOutbox() {
        NewTopic testTopic = new NewTopic("test", 1, (short)1);

        List<Outbox> outboxList = outboxRepository.findAll();

        outboxList.forEach(outbox -> {
            producer.sendMessage(
                    testTopic,
                    outbox
            );
            log.info(outbox.toString());
        });
    }
}
