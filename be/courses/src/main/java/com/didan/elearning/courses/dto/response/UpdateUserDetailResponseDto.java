package com.didan.elearning.courses.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(
    name = "UpdateUser",
    description = "The user update response body"
)
public class UpdateUserDetailResponseDto {
  @Schema(
      name = "userId",
      description = "The ID of the user",
      example = "123456"
  )
  private String userId;
  @Schema(
      name = "username",
      description = "The username of the user",
      example = "johndoe"
  )
  private String username;
  @Schema(
      name = "email",
      description = "The email of the user",
      example = "johndoe@gmail.com"
  )
  private String email;
  @Schema(
      name = "fullName",
      description = "The full name of the user",
      example = "John Doe"
  )
  private String fullName;
  @Schema(
      name = "dateOfBirth",
      description = "The date of birth of the user",
      example = "1990-01-01"
  )
  private String dateOfBirth;
  @Schema(
      name = "address",
      description = "The address of the user",
      example = "123 Main St, Springfield, IL 62701"
  )
  private String address;
  @Schema(
      name = "profilePicture",
      description = "The profile picture of the user",
      example = "https://example.com/profile.jpg"
  )
  private String profilePicture;
  @Schema(
      name = "phoneNumber",
      description = "The phone number of the user",
      example = "0123456890"
  )
  private String phoneNumber;
  @Schema(
      name = "gender",
      description = "Gender for the user",
      example = "MALE"
  )
  private String gender;
  @Schema(
      name = "studentCode",
      description = "The student code of the student",
      example = "D20201"
  )

  private String studentCode;
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
  @Pattern(regexp = "^\\d{4}$", message = "Start year must be 4 digits")
  private Integer startYear;
  @Schema(
      name = "isActive",
      description = "The status of the user",
      example = "true"
  )
  private Boolean isActive;
}
