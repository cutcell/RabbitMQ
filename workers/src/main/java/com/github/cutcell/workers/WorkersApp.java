package com.github.cutcell.workers;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class WorkersApp {

    @Bean
    public Queue workers(AppProps appProps) {
        return new Queue(appProps.getQueueName());
    }

    @Bean
    public Receiver receiver1(Runner runner, AppProps appProps) {
        Receiver r = new Receiver(runner, appProps);
        r.setId(1);
        return r;
    }

    @Bean
    public Receiver receiver2(Runner runner, AppProps appProps) {
        Receiver r = new Receiver(runner, appProps);
        r.setId(2);
        return r;
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkersApp.class, args);
    }
}
