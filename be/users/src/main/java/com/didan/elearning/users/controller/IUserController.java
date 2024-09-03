package com.didan.elearning.users.controller;

import com.didan.elearning.users.dto.error.ErrorDto;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
  @PostMapping("/create")
  public ResponseEntity<GeneralResponse<CreateUserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto);

  @Operation(
      summary = "Update a user",
      description = "Update a user with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Updated user successfully",
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
  @PatchMapping("/update/{id}")
  public ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> updateUser(@PathVariable("id") String userId, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto);
}
