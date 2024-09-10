package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BootRestProj09EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRestProj09EurekaServerApplication.class, args);
	}

}
