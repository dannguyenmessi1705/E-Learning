package com.didan.elearning.courses.dto.response;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "ClassResponseDto",
        description = "Response DTO for a class"
)
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class ClassResponseDto {
  @Schema(
      name = "classId",
      description = "The ID of the class",
      example = "123456"
  )
  private String classId;

  @Schema(
      name = "classCode",
      description = "The code of the class",
      example = "D2020-101"
  )
  private String classCode;


  @Schema(
      name = "instructor"
  )
  private UpdateUserDetailResponseDto instructor;

  @Schema(
      name = "assistant"
  )
  private UpdateUserDetailResponseDto assistant;

  @Schema(
      name = "className",
      description = "The name of the class",
      example = "Introduction to Computer Science"
  )
  @NotEmpty(message = "Class name is required")
  private String className;

  @Schema(
      name = "capacity",
      description = "The capacity of the class",
      example = "30"
  )
  @NotEmpty(message = "Capacity is required")
  @Pattern(regexp = "^[0-9]{1,2}$", message = "Capacity must be a number between 1 and 99")
  private Integer capacity;

  @Schema(
      name = "courseCode",
      description = "The code of the course",
      example = "CSA101"
  )
  @NotEmpty(message = "Course code is required")
  private String courseCode;
}
