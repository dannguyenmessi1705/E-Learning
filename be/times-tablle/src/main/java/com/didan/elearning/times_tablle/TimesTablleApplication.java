package com.didan.elearning.times_tablle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class TimesTablleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesTablleApplication.class, args);
	}

}
