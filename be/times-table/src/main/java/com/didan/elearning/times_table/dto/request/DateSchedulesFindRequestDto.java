package com.didan.elearning.times_table.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "DateSchedulesFindRequestDto",
    description = "The request body to find date schedules"
)
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class DateSchedulesFindRequestDto {
  @Schema(
      name = "weekNumber",
      description = "The week number to find date schedules",
      example = "1"
  )
  @NotBlank(message = "The week number is required")
  private String weekNumber;
  @Schema(
      name = "semesterCode",
      description = "The semester code to find date schedules",
      example = "2022-01"
  )
  @NotBlank(message = "The semester code is required")
  private String semesterCode;
}
