package com.didan.elearning.enrollments.controller;

import com.didan.elearning.enrollments.dto.error.ErrorDto;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsFilterRequest;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.ClassRegistrationDetailsResponseDto;
import com.didan.elearning.enrollments.dto.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/registration")
@Validated
@Tag(
    name = "Class Registration Details",
    description = "The controller to manage class registration details"
)
public interface IClassRegistrationDetailsController {

  @Operation(
      summary = "Create a class registration detail",
      description = "Create a class registration detail",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Created activity log successfully",
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
  ResponseEntity<GeneralResponse<ClassRegistrationDetailsResponseDto>> createClassRegistrationDetails(
      @Valid @RequestBody
      ClassRegistrationDetailsCreateRequestDto classRegistrationDetailsCreateRequestDto);

  @Operation(
      summary = "Update a class registration detail",
      description = "Update a class registration detail",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Updated class registration detail successfully",
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
  @PutMapping("/update/{registrationId}")
  ResponseEntity<GeneralResponse<ClassRegistrationDetailsResponseDto>> updateClassRegistrationDetails(
      @NotBlank(message = "Registration id is required") @PathVariable("registrationId") String registrationId,
      @RequestBody ClassRegistrationDetailsUpdateRequestDto classRegistrationDetailsUpdateRequestDto);

  @Operation(
      summary = "Get a class registration detail",
      description = "Get a class registration detail",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get class registration detail successfully",
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
  @GetMapping("/get/{registrationId}")
  ResponseEntity<GeneralResponse<ClassRegistrationDetailsResponseDto>> getClassRegistrationDetails(
      @NotBlank(message = "Registration id is required") @PathVariable("registrationId") String registrationId);

  @Operation(
      summary = "Get all class registration by filter",
      description = "Get all class registration by filter includes semester, course, class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get all class registration by filter successfully",
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
  @PostMapping("/filter")
  ResponseEntity<GeneralResponse<List<ClassRegistrationDetailsResponseDto>>> filterClassRegistrationDetails(
      @RequestBody ClassRegistrationDetailsFilterRequest classRegistrationDetailsFilterRequest);

  @Operation(
      summary = "Delete a class registration detail",
      description = "Delete a class registration detail",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Deleted class registration detail successfully",
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
  @DeleteMapping("/delete/{registrationId}")
  ResponseEntity<GeneralResponse<Void>> deleteClassRegistrationDetails(
      @NotBlank(message = "Registration id is required") @PathVariable("registrationId") String registrationId);
}
