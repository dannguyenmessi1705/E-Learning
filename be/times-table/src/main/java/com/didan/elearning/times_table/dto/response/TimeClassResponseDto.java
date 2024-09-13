package com.didan.elearning.times_table.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "TimeClassResponseDto",
    description = "The response body of a time class"
)
public class TimeClassResponseDto {
  @Schema(
      name = "timeClassId",
      description = "The time class ID",
      example = "125471"
  )
  private String timeClassId;
  @Schema(
      name = "instructorId",
      description = "The instructor ID of the time class",
      example = "125471"
  )
  private String instructorId;
  @Schema(
      name = "classCode",
      description = "The class code of the time class",
      example = "D2020-001"
  )
  private String classCode;
  @Schema(
      name = "className",
      description = "The class name of the time class",
      example = "Data Structure and Algorithm - D2020-001 - Nguyen Van A"
  )
  private String className;
  @Schema(
      name = "courseCode",
      description = "The course code of the time class",
      example = "DSA217"
  )
  private String courseCode;
}
