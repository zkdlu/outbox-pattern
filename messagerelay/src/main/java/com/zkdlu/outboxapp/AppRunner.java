package com.zkdlu.outboxapp;

import com.zkdlu.outboxapp.kafka.Producer;
import com.zkdlu.outboxapp.outbox.Outbox;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppRunner implements ApplicationRunner {
    private final Producer producer;

    public AppRunner(Producer producer) {
        this.producer = producer;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {

            producer.sendMessage(new NewTopic("test", 1, (short)1),
                    new Outbox(
                    "1",
                    "2",
                    "3",
                    "4",
                    LocalDateTime.now()
            ));

            Thread.sleep(5000);
        }
    }
}
