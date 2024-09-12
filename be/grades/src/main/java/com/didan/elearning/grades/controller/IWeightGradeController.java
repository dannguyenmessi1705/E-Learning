package com.didan.elearning.grades.controller;

import com.didan.elearning.grades.dto.request.WeightGradeRequestDto;
import com.didan.elearning.grades.dto.response.GeneralResponse;
import com.didan.elearning.grades.dto.response.WeightGradeResponseDto;
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

@RequestMapping("${spring.application.name}/v1/weight-grades")
@Validated
@Tag(
    name = "Weight Grade Controller",
    description = "The controller to manage weight grade"
)
public interface IWeightGradeController {

  @Operation(
      summary = "Create a new weight grade for a course",
      description = "Create a new weight grade for a course",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "The weight grade has been created successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          )
      }
  )
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<WeightGradeResponseDto>> createWeightGradeForCourse(
      @Valid @RequestBody WeightGradeRequestDto weightGradeRequestDto);

  @Operation(
      summary = "Update a weight grade for a course",
      description = "Update a weight grade for a course",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The weight grade has been updated successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          )
      }
  )
  @PutMapping("/update/{weightGradeId}")
  ResponseEntity<GeneralResponse<WeightGradeResponseDto>> updateWeightGradeForCourse(
      @NotBlank(message = "Weight grade id is required") @PathVariable("weightGradeId") String weightGradeId,
      @Valid @RequestBody WeightGradeRequestDto weightGradeRequestDto);

  @Operation(
      summary = "Get weight grade by course code",
      description = "Get weight grade by course code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The weight grade has been retrieved successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          )
      }
  )
  @GetMapping("/get/{courseCode}")
  ResponseEntity<GeneralResponse<WeightGradeResponseDto>> getWeightGradeByCourseCode(
      @NotBlank(message = "Course code is required") @PathVariable("courseCode") String courseCode);

  @Operation(
      summary = "Delete a weight grade",
      description = "Delete a weight grade",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The weight grade has been deleted successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          )
      }
  )
  @DeleteMapping("/delete/{weightGradeId}")
  ResponseEntity<GeneralResponse<Void>> deleteWeightGrade(
      @NotBlank(message = "Weight grade id is required") @PathVariable("weightGradeId") String weightGradeId);
}
