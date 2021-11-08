package com.zkdlu.messagerelay;

import com.zkdlu.messagerelay.kafka.Producer;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    private final Logger log = LoggerFactory.getLogger(AppRunner.class);
    private final Producer producer;

    public AppRunner(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        NewTopic newTopic = new NewTopic("test", 1, (short)1);

        while (true) {
            producer.sendMessage(newTopic, "hello");
            log.info("test");
            Thread.sleep(3000);
        }
    }
}
