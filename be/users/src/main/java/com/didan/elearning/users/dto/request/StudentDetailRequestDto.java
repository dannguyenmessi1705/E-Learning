package com.didan.elearning.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "StudentDetailRequestDto",
    description = "The user creation request body"
)
public class StudentDetailRequestDto {
  @Schema(
      name = "major",
      description = "The major of the student",
      example = "Computer Science"
  )
  private String major;
  @Schema(
      name = "startYear",
      description = "The start year of the student",
      example = "2020"
  )
  //  @Pattern(regexp = "^\\d{4}$", message = "Start year must be 4 digits")
  private Integer startYear;
}
