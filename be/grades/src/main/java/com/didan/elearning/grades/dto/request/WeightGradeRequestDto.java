package com.didan.elearning.grades.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "WeightGradeRequestDto",
    description = "The request body to request weight grade"
)
public class WeightGradeRequestDto {
  @Schema(
      name = "courseCode",
      description = "The code of course",
      example = "CSC101"
  )
  @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$|null", message = "The course code must be 3 capital letters followed by 3 digits, e.g. CSC101")
  private String courseCode;
  @Schema(
      name = "attendanceWeight",
      description = "The weight of attendance grade",
      example = "0.1"
  )
  @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$|null", message = "The attendance weight must be a number in format #.#")
  private String attendanceWeight;

  @Schema(
      name = "assignmentWeight",
      description = "The weight of assignment grade",
      example = "0.1"
  )
  @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$|null", message = "The assignment weight must be a number in format #.#")
  private String assignmentWeight;

  @Schema(
      name = "midtermWeight",
      description = "The weight of midterm grade",
      example = "0.1"
  )
  @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$|null", message = "The midterm weight must be a number in format #.#")
  private String midtermWeight;

  @Schema(
      name = "practiceWeight",
      description = "The weight of practice grade",
      example = "0.2"
  )
  @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$|null", message = "The practice weight must be a number in format #.#")
  private String practiceWeight;

  @Schema(
      name = "finalWeight",
      description = "The weight of final grade",
      example = "0.5"
  )
  @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$|null", message = "The final weight must be a number in format #.#")
  private String finalWeight;
}
