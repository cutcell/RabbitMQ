package com.github.cutcell.workers;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RabbitListener(queues = "${app.queueName}")
@Setter
@Slf4j
public class Receiver {

    private final Runner runner;
    private final AppProps appProps;

    private int id;

    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        doWork();
        watch.stop();
        log.debug("Receiver {} done work '{}' in {} ms", id, in, watch.getTime(TimeUnit.MILLISECONDS));
        runner.getLatch().countDown();
    }

    private void doWork() {
        try {
            Thread.sleep(new Random().nextInt((int) appProps.getWorkMaxDuration().toMillis()));
        } catch (InterruptedException e) {
            log.error("Interrupted", e);
        }
    }

}
