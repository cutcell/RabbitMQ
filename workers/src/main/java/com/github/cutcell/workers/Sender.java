package com.github.cutcell.workers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Getter
@Slf4j
public class Sender {

    private static final String MESSAGE_TEMPLATE = "Work_%d";

    private final AppProps appProps;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final CountDownLatch latch;

    @Autowired
    public Sender(AppProps appProps, RabbitTemplate rabbitTemplate, Queue queue) {
        this.appProps = appProps;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.latch = new CountDownLatch(appProps.getEmitNum());
    }

    public void send() {
        for (int i = 0; i < appProps.getEmitNum(); i++) {
            String msg = String.format(MESSAGE_TEMPLATE, i);
            log.debug("Sending '{}'", msg);
            rabbitTemplate.convertAndSend(queue.getName(), msg);
        }
    }
}
