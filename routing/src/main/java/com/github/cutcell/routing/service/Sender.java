package com.github.cutcell.routing.service;

import com.github.cutcell.routing.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    public void send(Collection<MessageDto> messages) {
        for (MessageDto dto : messages) {
            log.debug(">>> Sending: {}", dto);
            rabbitTemplate.convertAndSend(directExchange.getName(), dto.getRoutingKey(), dto);
        }
    }

}
