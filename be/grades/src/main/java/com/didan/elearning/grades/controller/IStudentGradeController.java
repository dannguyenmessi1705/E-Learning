package com.didan.elearning.grades.controller;

import com.didan.elearning.grades.dto.error.ErrorDto;
import com.didan.elearning.grades.dto.request.StudentGradeRequestDto;
import com.didan.elearning.grades.dto.response.GeneralResponse;
import com.didan.elearning.grades.dto.response.StudentGradeResponseDto;
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

@RequestMapping("${spring.application.name}/v1/student-grades")
@Validated
@Tag(
    name = "Student Grade",
    description = "The API provides a way to manage student grades"
)
public interface IStudentGradeController {
  @Operation(
      summary = "Create student grade",
      description = "Create student grade",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Student grade created successfully",
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
  ResponseEntity<GeneralResponse<StudentGradeResponseDto>> createStudentGrade(
      @Valid @RequestBody StudentGradeRequestDto studentGradeRequestDto);

  @Operation(
      summary = "Update student grade",
      description = "Update student grade",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student grade updated successfully",
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
  @PutMapping("/update/{studentGradeId}")
  ResponseEntity<GeneralResponse<StudentGradeResponseDto>> updateStudentGrade(
      @NotBlank(message = "Student grade id is required") @PathVariable("studentGradeId") String studentGradeId,
      @Valid @RequestBody StudentGradeRequestDto studentGradeRequestDto);

  @Operation(
      summary = "Get all student grades by student code",
      description = "Get all student grades by student code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student grade found",
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
  @GetMapping("/get/student/{studentCode}")
  ResponseEntity<GeneralResponse<List<StudentGradeResponseDto>>> getStudentGradeByStudentCode(
      @NotBlank(message = "Student code is required") @PathVariable("studentCode") String studentCode);

  @Operation(
      summary = "Get all student grades by class code",
      description = "Get all student grades by class code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student grade found",
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
  @GetMapping("/get/class/{classCode}")
  ResponseEntity<GeneralResponse<List<StudentGradeResponseDto>>> getStudentGradeByClassCode(
      @NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode);

  @Operation(
      summary = "Get student grade by student code and class code",
      description = "Get student grade by student code and class code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student grade found",
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
  @GetMapping("/get/{classCode}/{studentCode}")
  ResponseEntity<GeneralResponse<StudentGradeResponseDto>> getStudentGradeByClassCodeAndStudentCode(
      @NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode,
      @NotBlank(message = "Student code is required") @PathVariable("studentCode") String studentCode);

  @Operation(
      summary = "Delete student grade",
      description = "Delete student grade",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Student grade deleted successfully",
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
  @DeleteMapping("/delete/{studentGradeId}")
  ResponseEntity<GeneralResponse<Void>> deleteStudentGrade(
      @NotBlank(message = "Student grade id is required") @PathVariable("studentGradeId") String studentGradeId);
}
