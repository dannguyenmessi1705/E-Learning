package com.didan.elearning.materials.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Schema(
    name = "ClassMaterialRequestDto",
    description = "Class material request dto"
)
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class ClassMaterialRequestDto {
  @Schema(
      name = "timeClassInDateId",
      description = "Time class in date id",
      example = "213452"
  )
  @NotBlank(message = "Time class in date id is mandatory")
  private String timeClassInDateId;

  @Schema(
      name = "classCode",
      description = "Class code",
      example = "CSE101"
  )
  @NotBlank(message = "Class code is mandatory")
  private String classCode;

  @Schema(
      name = "instructorId",
      description = "Instructor id, who created the material",
      example = "123456"
  )
  @NotBlank(message = "Instructor id is mandatory")
  private String instructorId;

  @Schema(
      name = "title",
      description = "Title of the material",
      example = "Introduction to Java"
  )
  private String description;

  @Schema(
      name = "description",
      description = "Description of the material",
      example = "This is an introduction to Java"
  )
  private String title;

  @Schema(
      name = "file",
      description = "File to be uploaded"
  )
  @NotNull(message = "File is mandatory")
  private MultipartFile file;
}
