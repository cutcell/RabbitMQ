package com.github.cutcell.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;
    private final Sender sender;
    private final CountDownLatch latch;

    @Autowired
    public Runner(ConfigurableApplicationContext context, Sender sender, AppProps appProps) {
        this.context = context;
        this.sender = sender;
        this.latch = new CountDownLatch(appProps.getEmitsNum());
    }

    @Override
    public void run(String... args) throws Exception {
        sender.send();
        latch.await(120, TimeUnit.SECONDS);
        context.close();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
