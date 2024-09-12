package com.didan.elearning.grades.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "WeightGradeResponseDto",
    description = "The response body to response weight grade"
)
public class WeightGradeResponseDto {
  @Schema(
      name = "weightGradeId",
      description = "The id of weight grade",
      example = "1264b3b3-4b3b-4b3b-4b3b-4b3b4b3b4b3b"
  )
  private String weightGradeId;
  @Schema(
      name = "courseCode",
      description = "The code of course",
      example = "CSC101"
  )
  private String courseCode;
  @Schema(
      name = "attendanceWeight",
      description = "The weight of attendance grade",
      example = "0.1"
  )
  private Double attendanceWeight;

  @Schema(
      name = "assignmentWeight",
      description = "The weight of assignment grade",
      example = "0.1"
  )
  private Double assignmentWeight;

  @Schema(
      name = "midtermWeight",
      description = "The weight of midterm grade",
      example = "0.1"
  )
  private Double midtermWeight;

  @Schema(
      name = "practiceWeight",
      description = "The weight of practice grade",
      example = "0.2"
  )
  private Double practiceWeight;

  @Schema(
      name = "finalWeight",
      description = "The weight of final grade",
      example = "0.5"
  )
  private Double finalWeight;
}
