package com.didan.elearning.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "NotificationResponseDto",
    description = "Response body for a notification"
)
public class NotificationResponseDto {
  @Schema(
      name = "notificationId",
      description = "The ID of the notification",
      example = "123456"
  )
  private String notificationId;

  @Schema(
      name = "userId",
      description = "The ID of the user the notification is for",
      example = "123456"
  )
  private String userId;

  @Schema(
      name = "message",
      description = "The message of the notification",
      example = "You have a new message"
  )
  private String message;
  @Schema(
      name = "isRead",
      description = "The status of the notification",
      example = "false"
  )
  private String isRead;

}
