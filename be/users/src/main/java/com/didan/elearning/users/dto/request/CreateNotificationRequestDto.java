package com.didan.elearning.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "CreateNotificationRequestDto",
    description = "Request body for creating a notification"
)
public class CreateNotificationRequestDto {
  @NotBlank
  @Schema(
      name = "userId",
      description = "The ID of the user to create the notification for",
      example = "123456"
  )
  private String userId;

  @NotEmpty
  @Schema(
      name = "message",
      description = "The message to be displayed in the notification",
      example = "You have a new message"
  )
  private String message;
}
