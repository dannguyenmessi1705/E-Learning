package com.didan.elearning.grades.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "StudentGradeResponseDto",
    description = "The response body to response student grade"
)
public class StudentGradeResponseDto {
  @Schema(
      name = "studentGradeId",
      description = "The id of student grade",
      example = "1d2e3f4g-5h6i-7j8k-9l0m-a1b2c3d4e5f"
  )
  private String studentGradeId;

  @Schema(
      name = "studentCode",
      description = "The code of student has this grade",
      example = "D2020VT001"
  )
  private String studentCode;

  @Schema(
      name = "gradeClassId",
      description = "The id of grade class",
      example = "1264b3b3-4b3b-4b3b-4b3b-4b3b4b3b4b3b"
  )
  private String gradeClassId;

  @Schema(
      name = "attendanceScore",
      description = "The score of attendance",
      example = "10"
  )
  private Double attendanceScore;

  @Schema(
      name = "assignmentScore",
      description = "The score of assignment",
      example = "10"
  )
  private Double assignmentScore;

  @Schema(
      name = "midtermScore",
      description = "The score of midterm",
      example = "8"
  )
  private Double midtermScore;

  @Schema(
      name = "practiceScore",
      description = "The score of practice",
      example = "9"
  )
  private Double practiceScore;

  @Schema(
      name = "finalScore",
      description = "The score of final",
      example = "8"
  )
  private Double finalScore;

  @Schema(
      name = "totalScore",
      description = "The total score of student",
      example = "8.5"
  )
  private Double totalScore;

  @Schema(
      name = "typeGrade",
      description = "The type of grade",
      example = "A"
  )
  private String typeGrade;
}
