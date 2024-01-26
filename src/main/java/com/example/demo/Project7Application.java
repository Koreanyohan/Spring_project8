package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //날짜 변수 사용하려고
@SpringBootApplication
public class Project7Application {

	public static void main(String[] args) {
		SpringApplication.run(Project7Application.class, args);
	}

}
