package com.didan.elearning.grades.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "GradeClassResponseDto",
    description = "Grade Class Response DTO"
)
public class GradeClassResponseDto {
  @Schema(
      name = "gradeClassId",
      description = "The grade class id",
      example = "1d2e3f4g-5h6i-7j8k-9l0m-a1b2c3d4e5f"
  )
  private String gradeClassId;
  @Schema(
      name = "classCode",
      description = "The class will assign to this grade",
      example = "D2020-001"
  )
  private String classCode;
  @Schema(
      name = "weightGrade",
      description = "The weight grade will assign to this grade",
      implementation = WeightGradeResponseDto.class
  )
  private WeightGradeResponseDto weightGrade;
}
