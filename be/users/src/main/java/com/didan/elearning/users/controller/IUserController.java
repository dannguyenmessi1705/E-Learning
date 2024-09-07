package com.didan.elearning.users.controller;

import com.didan.elearning.users.dto.error.ErrorDto;
import com.didan.elearning.users.dto.request.ChangePasswordRequestDto;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.RoleResponseDto;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${spring.application.name}/v1")
@Tag(
    name = "User API",
    description = "The API for managing users"
)
public interface IUserController {
  @Operation(
      summary = "Create a new user",
      description = "Create a new user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Created user successfully",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<CreateUserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto);

  @Operation(
      summary = "Update a user",
      description = "Update a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Updated user successfully",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PatchMapping("/update/{id}")
  ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> updateUser(@PathVariable("id") String userId, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto);

  @Operation(
      summary = "Assign a role to a user",
      description = "Assign a role to a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Assigned role successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PatchMapping("/assign-role/{id}")
  ResponseEntity<GeneralResponse<RoleResponseDto>> assignRole(@PathVariable("id") String userId, @RequestParam("role") String roleName);

  @Operation(
      summary = "Unassign a role from a user",
      description = "Unassign a role from a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Unassigned role successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PatchMapping("/unassign-role/{id}")
  ResponseEntity<GeneralResponse<RoleResponseDto>> unassignRole(@PathVariable("id") String userId, @RequestParam("role") String roleName);

  @Operation(
      summary = "Delete a user",
      description = "Delete a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Deleted user successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @DeleteMapping("/delete/{id}")
  ResponseEntity<GeneralResponse<String>> deleteUser(@PathVariable("id") String userId);

  @Operation(
      summary = "Search for a user",
      description = "Search for a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Search user successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @GetMapping("/search")
  ResponseEntity<GeneralResponse<List<UpdateUserDetailResponseDto>>> searchUser(@RequestParam("q") String searchValue);

  @Operation(
      summary = "Get user details",
      description = "Get user details with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get user details successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @GetMapping("/get/{id}")
  ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> getUserDetails(@PathVariable("id") String userId);

  @Operation(
      summary = "Activate a user",
      description = "Activate a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Activated user successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PatchMapping("/activate/{id}")
  ResponseEntity<GeneralResponse<String>> activateUser(@PathVariable("id") String userId);

  @Operation(
      summary = "Deactivate a user",
      description = "Deactivate a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Deactivated user successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PatchMapping("/deactivate/{id}")
  ResponseEntity<GeneralResponse<String>> deactivateUser(@PathVariable("id") String userId);

  @Operation(
      summary = "Change password",
      description = "Change password with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Changed password successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PatchMapping("/change-password")
  ResponseEntity<GeneralResponse<String>> changePassword(@RequestBody @Valid
      ChangePasswordRequestDto changePasswordRequestDto);
}
