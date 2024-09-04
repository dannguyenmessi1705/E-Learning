package com.didan.elearning.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "CreateUser",
    description = "The user creation request body"
)
public class CreateUserResponseDto{
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
}
