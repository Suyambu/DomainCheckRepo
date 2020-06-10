package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAsync
@SpringBootApplication
public class DomainCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainCheckApplication.class, args);
	}

}
