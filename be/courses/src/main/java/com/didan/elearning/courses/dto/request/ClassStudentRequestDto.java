package com.didan.elearning.courses.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "ClassStudentRequestDto",
    description = "Request DTO for adding a student to a class"
)
public class ClassStudentRequestDto {
  @Schema(
      name = "studentCodes",
      description = "The list of student codes to add to the class",
      example = "[\"123456\", \"234567\"]"
  )
  @NotNull(message = "Student codes are required")
  private String[] studentCodes;

  @Schema(
      name = "classCode",
      description = "The code of the class",
      example = "D2020-101"
  )
  @NotBlank(message = "Class code is required")
  @Pattern(regexp = "^D[0-9]{4}-[0-9]{3}$", message = "Invalid class code")
  private String classCode;
}
