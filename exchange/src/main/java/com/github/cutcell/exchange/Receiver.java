package com.github.cutcell.exchange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Receiver {

    private final Runner runner;
    private final AppProps appProps;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) {
        receive(in, 2);
    }

    private void receive(String in, int id) {
        StopWatch watch = new StopWatch();
        watch.start();
        doWork(in);
        watch.stop();
        log.debug("Receiver {} done in {} ms", id, watch.getTime());
        runner.getLatch().countDown();
    }

    private void doWork(String in) {
        try {
            Thread.sleep(RandomUtils.nextLong(0, appProps.getWorkMaxDuration().toMillis()));
        } catch (InterruptedException e) {
            log.warn("Interrupted", e);
        }
    }

}
