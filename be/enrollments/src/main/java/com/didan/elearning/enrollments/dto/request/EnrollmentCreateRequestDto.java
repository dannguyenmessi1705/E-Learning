package com.didan.elearning.enrollments.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "EnrollmentCreateRequestDto",
    description = "The request body to create an enrollment"
)
public class EnrollmentCreateRequestDto {
  @Schema(
      name = "classDetailsId",
      description = "The class registration details id",
      example = "13b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"
  )
  @NotBlank(message = "Class details id is required")
  private String classDetailsId;

  @Schema(
      name = "studentCode",
      description = "The student code",
      example = "D2020VT001"
  )
  @NotBlank(message = "Student code is required")
  private String studentCode;
}
