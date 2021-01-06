package com.github.cutcell.routing.config;

import com.github.cutcell.routing.config.props.AppProps;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DirectExchange direct(AppProps appProps) {
        return new DirectExchange(appProps.getExchangeName());
    }

    @Bean
    public Queue queue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue queue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(directExchange).with("red");
    }

    @Bean
    public Binding binding2(DirectExchange directExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(directExchange).with("black");
    }

    @Bean
    public Binding binding3(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(directExchange).with("white");
    }

    @Bean
    public Binding binding4(DirectExchange directExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(directExchange).with("white");
    }

}
