package com.github.cutcell.routing;

import com.github.cutcell.routing.config.props.AppProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AppProps.class})
public class RoutingApp {
    public static void main(String[] args) {
        SpringApplication.run(RoutingApp.class, args);
    }
}
