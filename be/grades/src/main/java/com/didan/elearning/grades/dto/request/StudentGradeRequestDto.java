package com.didan.elearning.grades.dto.request;

import com.didan.elearning.grades.entity.GradeClass;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "StudentGradeRequestDto",
    description = "Student Grade Request DTO"
)
public class StudentGradeRequestDto {
  @Schema(
      name = "attendanceScore",
      description = "The score of attendance",
      example = "10"
  )
  @DecimalMin(value = "0", message = "The minimum value is 0")
  @DecimalMax(value = "10", message = "The maximum value is 10")
  private Double attendanceScore;

  @Schema(
      name = "assignmentScore",
      description = "The score of assignment",
      example = "10"
  )
  @DecimalMin(value = "0", message = "The minimum value is 0")
  @DecimalMax(value = "10", message = "The maximum value is 10")
  private Double assignmentScore;

  @Schema(
      name = "midtermScore",
      description = "The score of midterm",
      example = "8"
  )
  @DecimalMin(value = "0", message = "The minimum value is 0")
  @DecimalMax(value = "10", message = "The maximum value is 10")
  private Double midtermScore;

  @Schema(
      name = "practiceScore",
      description = "The score of practice",
      example = "9"
  )
  @DecimalMin(value = "0", message = "The minimum value is 0")
  @DecimalMax(value = "10", message = "The maximum value is 10")
  private Double practiceScore;

  @Schema(
      name = "finalScore",
      description = "The score of final",
      example = "8"
  )
  @DecimalMin(value = "0", message = "The minimum value is 0")
  @DecimalMax(value = "10", message = "The maximum value is 10")
  private Double finalScore;

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
}
