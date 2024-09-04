package com.didan.elearning.users.controller;

import com.didan.elearning.users.dto.error.ErrorDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("${spring.application.name}/v1/password/request")
@Tag(
    name = "Password Request API",
    description = "The API for password request"
)
public interface IPasswordRequestController {
  @Operation(
      summary = "Create password request",
      description = "Create password request with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Created password request successfully",
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
  @PostMapping("/forgot")
  ResponseEntity<GeneralResponse<String>> createPasswordRequest(
    @RequestParam
    @NotBlank
    @Email
    String email);
}
