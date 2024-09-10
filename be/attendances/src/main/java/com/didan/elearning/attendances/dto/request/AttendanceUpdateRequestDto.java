package com.didan.elearning.attendances.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Tag(
    name = "AttendanceRequestDto",
    description = "The request body for update attendance API"
)
public class AttendanceUpdateRequestDto {

  @Schema(
      name = "classCode",
      description = "The class code of the attendance",
      example = "D2020-101"
  )
  private String classCode;

  @Schema(
      name = "courseCode",
      description = "The course code of the attendance",
      example = "CSC101"
  )
  private String courseCode;

  @Schema(
      name = "semesterCode",
      description = "The semester code of the attendance",
      example = "2020-01"
  )
  private String semesterCode;

  @Schema(
      name = "attendanceTime",
      description = "The time when the attendance is taken",
      example = "2021-09-01T08:00:00"
  )
  private String attendanceTime;
}
