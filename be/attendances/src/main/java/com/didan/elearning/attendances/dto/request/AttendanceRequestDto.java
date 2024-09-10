package com.didan.elearning.attendances.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    description = "The request body for attendance API"
)
public class AttendanceRequestDto {

  @Schema(
      name = "classCode",
      description = "The class code of the attendance",
      example = "D2020-101"
  )
  @NotBlank(message = "Class code is required")
  @Pattern(regexp = "D[0-9]{4}-[0-9]{3}$", message = "Invalid class code format")
  private String classCode;

  @Schema(
      name = "courseCode",
      description = "The course code of the attendance",
      example = "CSC101"
  )
  @NotBlank(message = "Course code is required")
  @Pattern(regexp = "[A-Z]{3}[0-9]{3}$", message = "Invalid course code format")
  private String courseCode;

  @Schema(
      name = "semesterCode",
      description = "The semester code of the attendance",
      example = "2020-01"
  )
  @NotBlank(message = "Semester code is required")
  @Pattern(regexp = "[0-9]{4}-[0-9]{2}$", message = "Invalid semester code format")
  private String semesterCode;

  @Schema(
      name = "attendanceTime",
      description = "The time when the attendance is taken",
      example = "2021-09-01T08:00:00"
  )
  @NotBlank(message = "Attendance time is required")
  @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$", message = "Invalid attendance time format")
  private String attendanceTime;
}
