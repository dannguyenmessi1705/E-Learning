package com.didan.elearning.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
    name = "CreateUser",
    description = "The user creation request body"
)
public class CreateUserRequestDto {
  @Schema(
      name = "username",
      description = "The username of the user",
      example = "johndoe"
  )
  @NotBlank(message = "Username is required")
  @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
  private String username;
  @Schema(
      name = "email",
      description = "The email of the user",
      example = "johndoe@gmail.com"
  )
  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid")
  private String email;
  @Schema(
      name = "password",
      description = "The password of the user",
      example = "password"
  )
  @NotBlank(message = "Password is required")
  private String password;
  @Schema(
      name = "fullName",
      description = "The full name of the user",
      example = "John Doe"
  )
  @NotEmpty(message = "Full name is required")
  @Size(min = 6, max = 50, message = "Full name must be between 6 and 50 characters")
  private String fullName;
  @Schema(
      name = "dateOfBirth",
      description = "The date of birth of the user",
      example = "1990-01-01"
  )
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in the format yyyy-MM-dd")
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
  @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
  private String phoneNumber;
}
