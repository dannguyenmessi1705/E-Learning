package com.didan.elearning.materials.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(
    name = "ClassInDateResponseDto",
    description = "Class in date response data transfer object"
)
public class ClassInDateResponseDto {
  @Schema(
      name = "classInDateId",
      description = "The class in date ID",
      example = "1124432"
  )
  private String classInDateId;
  @Schema(
      name = "startTime",
      description = "Start time of the class",
      example = "08:00:00"
  )
  private LocalTime startTime;
  @Schema(
      name = "endTime",
      description = "End time of the class",
      example = "10:00:00"
  )
  private LocalTime endTime;
  @Schema(
      name = "classPeriod",
      description = "The class period",
      example = "1"
  )
  private Integer classPeriod;
  @Schema(
      name = "time",
      description = "The time",
      implementation = TimeClassResponseDto.class
  )
  private TimeClassResponseDto time;
  @Schema(
      name = "date",
      description = "The date",
      implementation = DateSchedulesResponseDto.class
  )
  private DateSchedulesResponseDto date;

}
