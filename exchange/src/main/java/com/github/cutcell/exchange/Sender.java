package com.github.cutcell.exchange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private static final String MESSAGE_TEMPLATE = "Fanout_exchange_%d";

    private final AppProps appProps;
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public void send() {
        for (int i = 0; i < appProps.getEmitsNum(); i++) {
            String msg = String.format(MESSAGE_TEMPLATE, i);
            log.debug(">>> Sending to fanout: '{}'", msg);
            rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", msg);
        }
    }

}
