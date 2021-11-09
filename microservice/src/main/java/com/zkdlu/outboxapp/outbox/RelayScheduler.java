package com.zkdlu.outboxapp.outbox;

import com.zkdlu.outboxapp.kafka.Producer;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;

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

    @Transactional
    @Scheduled(cron = "0/5 * * * * ?")
    public void relayFromOutbox() {
        NewTopic testTopic = new NewTopic("test", 1, (short)1);

        List<Outbox> outboxList = outboxRepository.findAll();

        log.info("{}, {}", Thread.currentThread().getId(), outboxList.size());

        outboxList.forEach(outbox -> {
            var future = producer.sendMessage(
                    testTopic,
                    outbox
            );

            future.addCallback(new ListenableFutureCallback<SendResult<String, Outbox>>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.info("[FAILURE] : " + outbox.toString());
                }

                @Transactional
                @Override
                public void onSuccess(SendResult<String, Outbox> result) {
                    log.info("[SUCCESS] [{}]: {}", Thread.currentThread().getId(), outbox.toString());
                    outboxRepository.delete(result.getProducerRecord().value());

                    log.info("???");
                }
            });

            log.info("{}, {}", Thread.currentThread().getId(), outbox.toString());

            throw new RuntimeException("???????");
        });
    }
}
