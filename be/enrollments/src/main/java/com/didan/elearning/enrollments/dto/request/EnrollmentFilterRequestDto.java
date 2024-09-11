package com.didan.elearning.enrollments.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "EnrollmentFilterRequestDto",
    description = "The request body to filter enrollments"
)
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class EnrollmentFilterRequestDto {
  @Schema(
      name = "studentCode",
      description = "The student code",
      example = "D2020VT001"
  )
  private String studentCode;
  @Schema(
      name = "classDetailsId",
      description = "The class registration details id",
      example = "13b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"
  )
  private String classDetailsId;
}
