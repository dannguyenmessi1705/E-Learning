package com.didan.elearning.courses;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Microservice for managing courses",
				description = "This microservice is responsible for managing courses in the e-learning platform",
				version = "${info.app.version}",
				contact = @Contact(
						name = "Nguyễn Di Đan",
						url = "https://iam.didan.id.vn",
						email = "didannguyen17@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0.html"
				),
				summary = "Microservice for managing courses"
		),
		servers = {
				@Server(
						description = "Local server",
						url = "http://localhost:8090"
				)
		}
)
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

}
