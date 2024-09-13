package com.didan.elearning.times_table.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "WeekSchedulesResponseDto",
    description = "The response body for week schedules"
)
public class WeekSchedulesResponseDto {
  @Schema(
      name = "weekScheduleId",
      description = "The ID of the week schedule",
      example = "1124-23124-21414124"
  )
  private String weekScheduleId;
  @Schema(
      name = "semesterCode",
      description = "The code of the semester",
      example = "2022-01"
  )
  private String semesterCode;
  @Schema(
      name = "weekNumber",
      description = "The number of the week",
      example = "1"
  )
  private Integer weekNumber;
  @Schema(
      name = "startWeekDate",
      description = "The start date of the week",
      example = "2022-01-01"
  )
  private LocalDate startWeekDate;
  @Schema(
      name = "endWeekDate",
      description = "The end date of the week",
      example = "2022-01-07"
  )
  private LocalDate endWeekDate;
}
