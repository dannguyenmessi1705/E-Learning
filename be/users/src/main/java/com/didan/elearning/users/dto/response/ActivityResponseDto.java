package com.didan.elearning.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "ActivityResponseDto",
    description = "The response DTO for activity logs"
)
public class ActivityResponseDto {
    @Schema(
        name = "activityLogId",
        description = "The unique identifier of the activity log",
        example = "1"
    )
    private String activityLogId;
  @Schema(
      name = "userId",
      description = "The unique identifier of the user",
      example = "1"
  )
  private String userId;
    @Schema(
        name = "description",
        description = "The description of the activity log",
        example = "User logged in"
    )
    private String description;
}
