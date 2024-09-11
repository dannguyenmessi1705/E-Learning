package com.didan.elearning.enrollments.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "EnrollmentUpdateRequestDto",
    description = "The request body to update an enrollment"
)
public class EnrollmentUpdateRequestDto {
  @Schema(
      name = "status",
      description = "The status of the enrollment",
      example = "ACTIVE"
  )
  @NotBlank(message = "Status is required")
  private String status;
}
