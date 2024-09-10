package com.didan.elearning.attendances;

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
        title = "Microservice for managing attendances in classes",
        description = "This microservice is responsible for managing attendances in classes",
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
        summary = "Microservice for managing attendances in classes"
    ),
    servers = {
        @Server(
            description = "Local server",
            url = "http://localhost:9000"
        )
    }
)
public class AttendancesApplication {

  public static void main(String[] args) {
    SpringApplication.run(AttendancesApplication.class, args);
  }

}
