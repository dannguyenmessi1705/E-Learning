package com.didan.elearning.courses.controller;

import com.didan.elearning.courses.dto.error.ErrorDto;
import com.didan.elearning.courses.dto.request.SemesterRequestDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.SemesterResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
    name = "Semester",
    description = "The semester API"
)
@RequestMapping("${spring.application.name}/v1/semesters")
@Validated
public interface ISemesterController {
  @Operation(
      summary = "Create a new semester",
      description = "API to create a new semester",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Semester created successfully",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(
                      implementation = ErrorDto.class
                  )
              )
          )
      }
  )
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<SemesterResponseDto>> createSemester(@Valid @RequestBody
      SemesterRequestDto semesterRequestDto);

  @Operation(
      summary = "Get all semesters",
      description = "API to get all semesters",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Semesters retrieved successfully",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(
                      implementation = ErrorDto.class
                  )
              )
          )
      }
  )
  @GetMapping("/all")
  ResponseEntity<GeneralResponse<List<SemesterResponseDto>>> getAllSemesters();
}
