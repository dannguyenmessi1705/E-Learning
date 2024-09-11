package com.didan.elearning.enrollments.controller;

import com.didan.elearning.enrollments.dto.error.ErrorDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentFilterRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.EnrollmentResponseDto;
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

@RequestMapping("${spring.application.name}/v1/enrollments")
@Validated
@Tag(
    name = "Enrollments",
    description = "The controller to manage enrollments"
)
public interface IEnrollmentController {

  @Operation(
      summary = "Create a enrollment",
      description = "Create a enrollment",
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
  ResponseEntity<GeneralResponse<EnrollmentResponseDto>> createEnrollment(@Valid @RequestBody
  EnrollmentCreateRequestDto enrollmentCreateRequestDto);

  @Operation(
      summary = "Update a enrollment",
      description = "Update a enrollment",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Updated activity log successfully",
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
  @PutMapping("/update/{enrollmentId}")
  ResponseEntity<GeneralResponse<EnrollmentResponseDto>> updateEnrollment(
      @NotBlank(message = "Enrollment ID is required") @PathVariable("enrollmentId") String enrollmentId,
      @RequestBody EnrollmentUpdateRequestDto enrollmentUpdateRequestDto
  );

  @Operation(
      summary = "Get a enrollment detail",
      description = "Get a enrollment detail by enrollment ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get enrollment detail successfully",
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
  @GetMapping("/get/{enrollmentId}")
  ResponseEntity<GeneralResponse<EnrollmentResponseDto>> getEnrollment(
      @NotBlank(message = "Enrollment ID is required") @PathVariable("enrollmentId") String enrollmentId
  );

  @Operation(
      summary = "Get all enrollments by filter",
      description = "Get all enrollments by filter includes classRegistrationId, studentCode",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get all enrollments successfully",
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
  ResponseEntity<GeneralResponse<List<EnrollmentResponseDto>>> getEnrollmentsByFilter(@RequestBody EnrollmentFilterRequestDto enrollmentFilterRequestDto);

  @Operation(
      summary = "Delete a enrollment",
      description = "Delete a enrollment by enrollment ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete enrollment successfully",
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
  @DeleteMapping("/delete/{enrollmentId}")
  ResponseEntity<GeneralResponse<Void>> deleteEnrollment(
      @NotBlank(message = "Enrollment ID is required") @PathVariable("enrollmentId") String enrollmentId
  );
}
