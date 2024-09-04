package com.didan.elearning.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "ChangePasswordRequest",
    description = "The change password request body"
)
public class ChangePasswordRequestDto {
  @Schema(
      name = "token",
      description = "The token of the user to validate the request",
      example = "6CCyTYnwZylg9SnGvVkk"
  )
  @NotBlank(message = "Token is required")
  private String token;
  @Schema(
      name = "password",
      description = "The new password of the user",
      example = "password"
  )
  @NotBlank(message = "Password is required")
  private String password;
}
