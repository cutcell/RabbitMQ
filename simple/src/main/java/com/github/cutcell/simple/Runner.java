package com.github.cutcell.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;
    private final Sender sender;

    @Override
    public void run(String... args) throws Exception {
        sender.send();
        Thread.sleep(5000);
        context.close();
    }
}
