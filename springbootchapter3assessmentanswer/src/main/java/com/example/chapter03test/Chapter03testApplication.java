package com.example.chapter03test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Chapter03testApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter03testApplication.class, args);
	}

}
