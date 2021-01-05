package com.github.cutcell.workers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;
    private final Sender sender;
    private final CountDownLatch latch;

    @Autowired
    public Runner(ConfigurableApplicationContext context, AppProps appProps, Sender sender) {
        this.context = context;
        this.sender = sender;
        this.latch = new CountDownLatch(appProps.getEmitNum());
    }

    @Override
    public void run(String... args) throws Exception {
        sender.send();
        if (!latch.await(120, TimeUnit.SECONDS)) {
            log.warn("Latch waiting time elapsed before the count reached zero");
        }
        context.close();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
