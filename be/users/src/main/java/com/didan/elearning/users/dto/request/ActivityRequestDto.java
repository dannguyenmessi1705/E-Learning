package com.didan.elearning.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "ActivityRequestDto",
    description = "The request DTO for activity logs"
)
public class ActivityRequestDto {
  @Schema(
      name = "description",
      description = "The description of the activity log",
      example = "User logged in"
  )
  @NotEmpty
  private String description;
}
