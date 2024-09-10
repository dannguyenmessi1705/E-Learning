package com.didan.elearning.attendances.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Tag(
    name = "AttendanceResponseDto",
    description = "The response body for attendance API"
)
public class AttendanceResponseDto {

  @Schema(
      name = "attendanceId",
      description = "The ID of the attendance",
      example = "123e4567-e89b-12d3-a456-426614174000"
  )
  private String attendanceId;
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
  private LocalDateTime attendanceTime;
}
