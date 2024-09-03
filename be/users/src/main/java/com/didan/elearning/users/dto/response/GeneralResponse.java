package com.didan.elearning.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "GeneralResponse",
    description = "The general response body"
)
public class GeneralResponse<T> {
  private Integer statusCode;
  private String message;
  private T data;
}
