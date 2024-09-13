package com.didan.elearning.times_table.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "TimeClassRequestDto",
    description = "The request body to create a time class"
)
public class TimeClassRequestDto {
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
      name = "courseCode",
      description = "The course code of the time class",
      example = "DSA217"
  )
  private String courseCode;
}
