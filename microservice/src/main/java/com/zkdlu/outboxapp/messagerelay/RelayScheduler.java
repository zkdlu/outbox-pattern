package com.zkdlu.outboxapp.messagerelay;

import com.zkdlu.outboxapp.kafka.Producer;
import com.zkdlu.outboxapp.outbox.Outbox;
import com.zkdlu.outboxapp.outbox.OutboxRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Component
public class RelayScheduler {
    private final OutboxRepository outboxRepository;
    private final Producer producer;

    public RelayScheduler(OutboxRepository outboxRepository, Producer producer) {
        this.outboxRepository = outboxRepository;
        this.producer = producer;
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void relayFromOutbox() {
        NewTopic testTopic = new NewTopic("test", 1, (short)1);
        List<Outbox> outboxList = outboxRepository.findAll();

        List<Long> sentIdList = new ArrayList<>();

        outboxList.forEach(outbox -> {
            try {
                producer.sendMessage(testTopic, outbox.getAggregateId(), outbox).get();
                sentIdList.add(outbox.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        outboxRepository.deleteAllById(sentIdList);
    }
}
