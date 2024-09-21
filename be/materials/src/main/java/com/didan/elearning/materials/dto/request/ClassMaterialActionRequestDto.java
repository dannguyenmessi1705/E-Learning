package com.didan.elearning.materials.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name = "ClassMaterialActionRequestDto",
    description = "Class material copy request dto"
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassMaterialActionRequestDto {
  @Schema(
      name = "fileName",
      description = "File name",
      example = "[\"file.pdf\", \"file2.pdf\"]"
  )
  @NotNull(message = "File name is required")
  @NotEmpty(message = "File name is required")
  private List<String> fileName;

  @Schema(
      name = "srcClassCode",
      description = "Source class code",
      example = "CSE101"
  )
  @NotBlank(message = "Source class code is required")
  private String srcClassCode;

  @Schema(
      name = "destClassCode",
      description = "Destination class code",
      example = "CSE102"
  )
  private String destClassCode;
}
