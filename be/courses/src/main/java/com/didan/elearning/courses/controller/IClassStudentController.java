package com.didan.elearning.courses.controller;

import com.didan.elearning.courses.dto.error.ErrorDto;
import com.didan.elearning.courses.dto.request.ClassStudentRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.UpdateUserDetailResponseDto;
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
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/student")
@Validated
@Tag(
    name = "Class Student Controller",
    description = "Endpoints for managing students in classes"
)
public interface IClassStudentController {
  @Operation(
      summary = "Add students to a class",
      description = "Add students to a class",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Students added to class successfully",
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
  @PostMapping("/add")
  ResponseEntity<GeneralResponse<String>> addStudentsToClass(@Valid @RequestBody ClassStudentRequestDto classStudentRequestDto);

  @Operation(
      summary = "Remove students from a class",
      description = "Remove students from a class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Students removed from class successfully",
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
  @DeleteMapping("/remove")
  ResponseEntity<GeneralResponse<String>> removeStudentsFromClass(@Valid @RequestBody ClassStudentRequestDto classStudentRequestDto);

  @Operation(
      summary = "Remove all students from a class",
      description = "Remove all students from a class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All students removed from class successfully",
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
  @DeleteMapping("/remove/all/class/{classCode}")
  ResponseEntity<GeneralResponse<String>> removeAllStudentsFromClass(@NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode);

  @Operation(
      summary = "Remove all classes of a student",
      description = "Remove all classes of a student",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All classes of student removed successfully",
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
  @DeleteMapping("/remove/all/{studentCode}")
  ResponseEntity<GeneralResponse<String>> removeAllClassesOfStudent(@NotBlank(message = "Student code is required") @PathVariable("studentCode") String studentCode);

  @Operation(
      summary = "Get all classes of a student",
      description = "Get all classes of a student",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All classes of student retrieved successfully",
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
  @GetMapping("/get/all/class{studentCode}")
  ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getAllClassesOfStudent(@NotBlank(message = "Student code is required") @PathVariable("studentCode") String studentCode);

  @Operation(
      summary = "Get all students of a class",
      description = "Get all students of a class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All students of class retrieved successfully",
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
  @GetMapping("/get/all/student/{classCode}")
  ResponseEntity<GeneralResponse<List<UpdateUserDetailResponseDto>>> getAllStudentsOfClass(@NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode);
}
