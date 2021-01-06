package com.github.cutcell.routing.service;

import com.github.cutcell.routing.Runner;
import com.github.cutcell.routing.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Receiver {

    private final Runner runner;

    @RabbitListener(queues = "#{queue1.name}")
    public void receive1(MessageDto dto) {
        log.debug("Received: {}", dto);
        runner.getLatch().countDown();
    }

    @RabbitListener(queues = "#{queue2.name}")
    public void receive2(MessageDto dto) {
        log.debug("Received: {}", dto);
        runner.getLatch().countDown();
    }

}
