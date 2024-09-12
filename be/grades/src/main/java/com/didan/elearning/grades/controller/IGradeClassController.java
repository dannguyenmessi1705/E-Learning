package com.didan.elearning.grades.controller;

import com.didan.elearning.grades.dto.error.ErrorDto;
import com.didan.elearning.grades.dto.request.GradeClassRequestDto;
import com.didan.elearning.grades.dto.response.GeneralResponse;
import com.didan.elearning.grades.dto.response.GradeClassResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/grade-class")
@Validated
@Tag(
    name = "Grade Class Controller",
    description = "APIs for managing grade class"
)
public interface IGradeClassController {
  @Operation(
      summary = "Create grade class",
      description = "Create a new grade class",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Grade class created successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<GradeClassResponseDto>> createGradeClass(
      @Valid @RequestBody GradeClassRequestDto gradeClassRequestDto);

  @Operation(
      summary = "Update grade class",
      description = "Update an existing grade class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Grade class updated successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PutMapping("/update/{gradeClassId}")
  ResponseEntity<GeneralResponse<GradeClassResponseDto>> updateGradeClass(
      @NotBlank(message = "Grade class id is required") @PathVariable("gradeClassId") String gradeClassId,
      @Valid @RequestBody GradeClassRequestDto gradeClassRequestDto);

  @Operation(
      summary = "Get grade class by class code",
      description = "Get an existing grade class by class code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Grade class retrieved successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @GetMapping("/get/{classCode}")
  ResponseEntity<GeneralResponse<GradeClassResponseDto>> getGradeByClassCode(
      @NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode);

  @Operation(
      summary = "Delete grade class",
      description = "Delete an existing grade class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Grade class deleted successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @DeleteMapping("/delete/{gradeClassId}")
  ResponseEntity<GeneralResponse<Void>> deleteGradeClass(
      @NotBlank(message = "Grade class id is required") @PathVariable("gradeClassId") String gradeClassId);
}
