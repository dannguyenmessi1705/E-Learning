package com.didan.elearning.enrollments.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "ClassRegistrationDetailsFilterRequest",
    description = "The request body to filter class registration details"
)
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class ClassRegistrationDetailsFilterRequest {
  @Schema(
      name = "classCode",
      description = "The class code to get the registration details",
      example = "D2020-111"
  )
  private String classCode;
  @Schema(
      name = "semesterCode",
      description = "The semester code to get the registration details",
      example = "2020-01"
  )
  private String semesterCode;
  @Schema(
      name = "courseCode",
      description = "The course code to get the registration details",
      example = "CSC101"
  )
  private String courseCode;
}
