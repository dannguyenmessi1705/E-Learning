package com.didan.elearning.courses.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
    name = "SemesterResponse",
    description = "The response body for a semester"
)
public class SemesterResponseDto {
  @Schema(
      name = "semesterId",
      description = "The ID of the semester",
      example = "1"
  )
  private String semesterId;
  @Schema(
      name = "semesterCode",
      description = "The code of the semester",
      example = "2022-01"
  )
  private String semesterCode;
  @Schema(
      name = "name",
      description = "The name of the semester",
      example = "Semseter 1 in 2022"
  )
  private String name;
  @Schema(
      name = "startDate",
      description = "The start date of the semester",
      example = "2022-01-01"
  )
  private String startDate;
  @Schema(
      name = "endDate",
      description = "The end date of the semester",
      example = "2022-05-31"
  )
  private String endDate;
}
