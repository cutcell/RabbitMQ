package com.github.cutcell.workers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private static final String MESSAGE_TEMPLATE = "Job_%d";

    private final AppProps appProps;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void send() {
        for (int i = 0; i < appProps.getEmitNum(); i++) {
            String msg = String.format(MESSAGE_TEMPLATE, i);
            log.debug("Sending '{}'", msg);
            rabbitTemplate.convertAndSend(queue.getName(), msg);
        }
    }
}
