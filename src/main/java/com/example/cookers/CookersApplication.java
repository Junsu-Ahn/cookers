package com.example.cookers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CookersApplication {

	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(CookersApplication.class, args);
	}
}
