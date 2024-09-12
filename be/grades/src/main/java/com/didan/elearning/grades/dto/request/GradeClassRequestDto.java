package com.didan.elearning.grades.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "GradeClassRequestDto",
    description = "Grade Class Request DTO"
)
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class GradeClassRequestDto {
  @Schema(
      name = "classCode",
      description = "The class will assign to this grade",
      example = "D2020-001"
  )
  private String classCode;
  @Schema(
      name = "weightGradeId",
      description = "The weight grade will assign to this grade",
      example = "1d2e3f4g-5h6i-7j8k-9l0m-a1b2c3d4e5f"
  )
  private String weightGradeId;
}
