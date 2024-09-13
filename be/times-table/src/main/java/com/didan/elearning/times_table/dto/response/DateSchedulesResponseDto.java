package com.didan.elearning.times_table.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(
    name = "DateSchedulesResponseDto",
    description = "The response body for date schedules"
)
public class DateSchedulesResponseDto {

  @Schema(
      name = "dateScheduleId",
      description = "The date schedule ID",
      example = "121434"
  )
  private String dateScheduleId;
  @Schema(
      name = "weekSchedulesId",
      description = "The id of the week schedules",
      example = "1234"
  )
  private String weekSchedulesId;
  @Schema(
      name = "date",
      description = "The date of the schedule",
      example = "2021-12-12"
  )
  private LocalDate date;
  @Schema(
      name = "day",
      description = "The day of the week",
      example = "Monday"
  )
  private String day; // DayConstant

  @Schema(
      name = "isDayOff",
      description = "The status of the date",
      example = "true"
  )
  private String isDayOff;
}
