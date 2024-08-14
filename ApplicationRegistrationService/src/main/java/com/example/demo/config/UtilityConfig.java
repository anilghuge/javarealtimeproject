package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UtilityConfig {

	@Bean(name = "template")
	RestTemplate createTemplate() {
		return new RestTemplate();
	}
	
	@Bean(name="webClient")
	WebClient createWebClient() {
		return WebClient.create(); 
	}
}
