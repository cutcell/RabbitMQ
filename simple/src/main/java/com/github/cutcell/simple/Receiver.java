package com.github.cutcell.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "simple")
@Slf4j
public class Receiver {

    @RabbitHandler
    public void receive(String in) {
        log.debug("<<< received: " + in);
    }

}
