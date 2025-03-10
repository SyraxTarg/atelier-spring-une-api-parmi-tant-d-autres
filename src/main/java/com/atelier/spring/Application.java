package com.atelier.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8085"));
		app.run(args);
	}
	@Bean
	public RestClient restClient() {
		return RestClient.create();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
