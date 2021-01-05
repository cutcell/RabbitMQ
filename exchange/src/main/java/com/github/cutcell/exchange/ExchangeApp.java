package com.github.cutcell.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ExchangeApp {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeApp.class, args);
    }
}
