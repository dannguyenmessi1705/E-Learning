package com.didan.elearning.grades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class GradesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradesApplication.class, args);
	}

}
