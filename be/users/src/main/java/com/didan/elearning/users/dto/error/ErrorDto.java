package com.didan.elearning.users.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Data
@Schema(
    name = "Error",
    description = "The error response body"
)
public class ErrorDto {
  @Schema(description = "API path invoked by client")
  private String apiPath;
  @Schema(description = "Error code representing the error happened")
  private HttpStatus errorCode;
  @Schema(description = "Error message describing the error happened")
  private Object errorMsg;
  @Schema(description = "Time when the error happened")
  private LocalDateTime errorTime;
}
