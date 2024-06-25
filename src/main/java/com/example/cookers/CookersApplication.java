package com.example.cookers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;

@SpringBootApplication
@EnableJpaAuditing
public class CookersApplication {

	public static void main(String[] args) {
		File file = new File("src/main/resources/templates/recipe/recipe_create_form.html");
		if (file.exists()) {
			System.out.println("템플릿 파일이 존재합니다.");
		} else {
			System.out.println("템플릿 파일이 존재하지 않습니다.");
		}
		SpringApplication.run(CookersApplication.class, args);
	}
}
