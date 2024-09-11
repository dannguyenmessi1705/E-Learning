package com.didan.elearning.enrollments.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(
    name = "EnrollmentCreateRequestDto",
    description = "The request body to create an enrollment"
)
public class EnrollmentResponseDto {
  @Schema(
      name = "enrollmentId",
      description = "The enrollment id",
      example = "13b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"
  )
  private String enrollmentId;
  @Schema(
      name = "classDetailsId",
      description = "The class registration details id",
      example = "13b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"
  )
  private String classDetailsId;

  @Schema(
      name = "studentId",
      description = "The student code",
      example = "D2020VT001"
  )
  private String studentCode;
  @Schema(
      name = "status",
      description = "The status of the enrollment",
      example = "ACTIVE"
  )
  private String status;
}
