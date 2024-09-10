package com.didan.elearning.attendances.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "RecordUpdateRequestDto",
    description = "The request body for creating a record"
)
public class RecordUpdateRequestDto {
  @Schema(
      name = "studentCode",
      description = "The student code",
      example = "D2020001"
  )
  private String studentCode;
  @Schema(
      name = "status",
      description = "The status of the student",
      example = "PRESENT"
  )
  @NotBlank(message = "Status is required")
  private String status;
}
