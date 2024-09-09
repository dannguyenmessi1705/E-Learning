package com.didan.elearning.courses.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "ClassUpdateRequestDto",
    description = "Request DTO for updating a class"
)
public class ClassUpdateRequestDto {
  @Schema(
      name = "classCode",
      description = "The code of the class",
      example = "D2020-101"
  )
  @NotEmpty(message = "Class code is required")
  private String classCode;

  @Schema(
      name = "instructorId",
      description = "The ID of the instructor",
      example = "123456"
  )
  private String instructorId;

  @Schema(
      name = "assistantId",
      description = "The ID of the assistant",
      example = "123456"
  )
  private String assistantId;

  @Schema(
      name = "className",
      description = "The name of the class",
      example = "Introduction to Computer Science"
  )
  private String className;

  @Schema(
      name = "capacity",
      description = "The capacity of the class",
      example = "30"
  )
  private Integer capacity;

  @Schema(
      name = "courseCode",
      description = "The code of the course",
      example = "CSA101"
  )
  private String courseCode;
}
