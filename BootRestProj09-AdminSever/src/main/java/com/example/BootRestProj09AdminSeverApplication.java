package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class BootRestProj09AdminSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRestProj09AdminSeverApplication.class, args);
	}

}
