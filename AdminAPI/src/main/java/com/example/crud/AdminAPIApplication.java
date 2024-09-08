package com.example.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminAPIApplication.class, args);
	}

}
