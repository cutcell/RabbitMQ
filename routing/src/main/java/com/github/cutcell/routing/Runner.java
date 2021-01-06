package com.github.cutcell.routing;

import com.github.cutcell.routing.dto.MessageDto;
import com.github.cutcell.routing.service.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;
    private final Sender sender;

    private CountDownLatch latch;

    @Override
    public void run(String... args) throws Exception {
        List<MessageDto> messages = new ArrayList<>();
        messages.add(new MessageDto(UUID.randomUUID().toString(), "black", "data"));
        messages.add(new MessageDto(UUID.randomUUID().toString(), "red", "data"));
        messages.add(new MessageDto(UUID.randomUUID().toString(), "white", "data"));
        messages.add(new MessageDto(UUID.randomUUID().toString(), "black", "data"));
        messages.add(new MessageDto(UUID.randomUUID().toString(), "green", "data"));

        latch = new CountDownLatch(messages.size());

        sender.send(messages);

        latch.await(180, TimeUnit.SECONDS);

        context.close();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
