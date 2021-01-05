package com.github.cutcell.simple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private static final String MESSAGE_TEMPLATE = "This is a test message %d";

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Value("${app.emitNum:10}")
    private int emitNum;

    public void send() {
        for (int i = 0; i < emitNum; i++) {
            String msg = String.format(MESSAGE_TEMPLATE, i);
            log.debug(">>> sending: {}", msg);
            rabbitTemplate.convertAndSend(queue.getName(), msg);
        }
    }
}
