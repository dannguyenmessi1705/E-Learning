package com.didan.elearning.times_table.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "DateSchedulesUpdateRequestDto",
    description = "The request body to update date schedules"
)
public class DateSchedulesUpdateRequestDto {
  @Schema(
      name = "date",
      description = "The date to update",
      example = "2021-09-01"
  )
  @NotBlank(message = "Date must not be blank")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in format yyyy-MM-dd")
  private String date;

  @Schema(
      name = "isDayOff",
      description = "The day off status of the date",
      example = "true"
  )
  @NotBlank(message = "Day off status must not be blank")
  @Pattern(regexp = "^(true|false)$", message = "Day off status must be either true or false")
  private String isDayOff;
}
