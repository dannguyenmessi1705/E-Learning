package com.didan.elearning.times_table.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "ClassInDateRequestDto",
    description = "Class in date request data transfer object"
)
public class ClassInDateRequestDto {
  @Schema(
      name = "startTime",
      description = "Start time of the class",
      example = "08:00:00"
  )
  private String startTime;
  @Schema(
      name = "endTime",
      description = "End time of the class",
      example = "10:00:00"
  )
  private String endTime;
  @Schema(
      name = "date",
      description = "Date of the class",
      example = "2022-01-01"
  )
  private String date;
  @Schema(
      name = "classCode",
      description = "The class code",
      example = "D2020-222"
  )
  private String classCode;
  @Schema(
      name = "courseCode",
      description = "The course code",
      example = "DSA132"
  )
  private String courseCode;
  @Schema(
      name = "instructorId",
      description = "The instructor ID",
      example = "4141313"
  )
  private String instructorId;
}
