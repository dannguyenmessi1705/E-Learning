package com.didan.elearning.enrollments.dto.request;

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
    name = "ClassRegistrationDetailsCreateRequestDto",
    description = "The request body to get class registration details"
)
public class ClassRegistrationDetailsCreateRequestDto {
  @Schema(
      name = "classCode",
      description = "The class code to get the registration details",
      example = "D2020-111"
  )
  @NotBlank(message = "Class code is required")
  @Pattern(regexp = "D[0-9]{4}-[0-9]{3}", message = "Class code must be in format DYYYY-NNN")
  private String classCode;
  @Schema(
      name = "semesterCode",
      description = "The semester code to get the registration details",
      example = "2020-01"
  )
  @NotBlank(message = "Semester code is required")
  @Pattern(regexp = "[0-9]{4}-[0-9]{2}", message = "Semester code must be in format YYYY-NN")
  private String semesterCode;
  @Schema(
      name = "courseCode",
      description = "The course code to get the registration details",
      example = "CSC101"
  )
  @NotBlank(message = "Course code is required")
  private String courseCode;
  @Schema(
      name = "maxCapacity",
      description = "The maximum capacity of the class",
      example = "30"
  )
  @NotNull(message = "Max capacity is required")
  private Integer maxCapacity;

  @Schema(
      name = "statusClass",
      description = "The status of the class registration",
      example = "OPEN"
  )
  @NotBlank(message = "Status class is required")
  private String statusClass; // ClassStatusConstant
}
