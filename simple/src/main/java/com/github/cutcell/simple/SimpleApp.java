package com.github.cutcell.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleApp {

	@Bean
	public Queue simple() {
		return new Queue("simple");
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleApp.class, args);
	}

}
