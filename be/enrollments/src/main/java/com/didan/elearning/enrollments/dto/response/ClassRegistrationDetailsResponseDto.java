package com.didan.elearning.enrollments.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "ClassRegistrationDetailsResponseDto",
    description = "The response body to get class registration details"
)
public class ClassRegistrationDetailsResponseDto {
  @Schema(
      name = "classDetailsId",
      description = "The class details id",
      example = "13b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"
  )
  private String classDetailsId;
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
  @Schema(
      name = "maxCapacity",
      description = "The maximum capacity of the class",
      example = "30"
  )
  private Integer maxCapacity;

  @Schema(
      name = "currentEnrollmentQuantity",
      description = "The current enrollment quantity of the class",
      example = "20"
  )
  private Integer currentEnrollmentQuantity;

  @Schema(
      name = "statusClass",
      description = "The status of the class registration",
      example = "OPEN"
  )
  private String statusClass; // ClassStatusConstant
}
