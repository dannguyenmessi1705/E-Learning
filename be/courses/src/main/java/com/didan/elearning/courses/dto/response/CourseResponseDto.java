package com.didan.elearning.courses.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "CourseResponse",
    description = "The course request body"
)
public class CourseResponseDto {
  @Schema(
      name = "courseId",
      description = "The ID of the course",
      example = "123456"
  )
  private String courseId;
  @Schema(
      name = "courseCode",
      description = "The code of the course",
      example = "CSA101"
  )
  @NotEmpty(message = "Course code is required")
  @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "Course code must be in the format of AAA111")
  private String courseCode;

  @Schema(
      name = "courseName",
      description = "The name of the course",
      example = "Introduction to Computer Science"
  )
  @NotEmpty(message = "Course name is required")
  private String courseName;

  @Schema(
      name = "credit",
      description = "The credit of the course",
      example = "3"
  )
  @NotEmpty(message = "Credit is required")
  @Pattern(regexp = "^[0-9]$", message = "Credit must be a number")
  private String credit;

  @Schema(
      name = "description",
      description = "The description of the course",
      example = "This course introduces students to the field of computer science."
  )
  @NotEmpty(message = "Description is required")
  private String description;
}
