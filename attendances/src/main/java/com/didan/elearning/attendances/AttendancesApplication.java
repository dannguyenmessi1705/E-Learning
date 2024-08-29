package com.didan.elearning.attendances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AttendancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendancesApplication.class, args);
	}

}
