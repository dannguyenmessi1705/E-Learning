package com.didan.elearning.courses.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "SemesterRequest",
    description = "The request body for creating a new semester"
)
public class SemesterRequestDto {
  @Schema(
      name = "semesterCode",
      description = "The code of the semester",
      example = "2022-01"
  )
  @NotEmpty(message = "Semester code is required")
  @Pattern(regexp = "^[0-9]{4}-[0-9]{2}$", message = "Semester code must be in the format year(4)-number(2)")
  private String semesterCode;

  @Schema(
      name = "startDate",
      description = "The start date of the semester",
      example = "2022-01-01"
  )
  @NotEmpty(message = "Start date is required")
  @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Start date must be in the format yyyy-MM-dd")
  private String startDate;

  @Schema(
      name = "endDate",
      description = "The end date of the semester",
      example = "2022-05-31"
  )
  @NotEmpty(message = "End date is required")
  @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "End date must be in the format yyyy-MM-dd")
  private String endDate;
}
