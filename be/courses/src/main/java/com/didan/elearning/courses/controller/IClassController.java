package com.didan.elearning.courses.controller;

import com.didan.elearning.courses.dto.error.ErrorDto;
import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.request.ClassUpdateRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
    name = "Class Controller",
    description = "Endpoints for managing classes"
)
@RequestMapping("${spring.application.name}/v1/classes")
@Validated
public interface IClassController {
  @Operation(
      summary = "Create a class",
      description = "Create a class for a course",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Course assigned to semester successfully",
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
  ResponseEntity<GeneralResponse<ClassResponseDto>> createClass(@Valid @RequestBody ClassRequestDto classRequestDto);

  @Operation(
      summary = "Get all classes of a course",
      description = "Get all classes of a course",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Classes retrieved successfully",
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
  @GetMapping("/all/{courseCode}")
  ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getClassesOfCourse(@NotEmpty(message = "Course code is required") @PathVariable("courseCode") String courseCode);

  @Operation(
      summary = "Get all classes by instructor",
      description = "Get all classes by instructor",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Classes retrieved successfully",
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
  @GetMapping("/instructor/{instructorId}")
  ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getClassesByInstructor(@NotEmpty(message = "Instructor ID is required") @PathVariable("instructorId") String instructorId);

  @Operation(
      summary = "Get all classes by assistant",
      description = "Get all classes by assistant",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Classes retrieved successfully",
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
  @GetMapping("/assistant/{assistantId}")
  ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getClassesByAssistant(@NotEmpty(message = "Assistant ID is required") @PathVariable("assistantId")String assistantId);

  @Operation(
      summary = "Update a class",
      description = "Update a class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Class updated successfully",
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
  @PostMapping("/update")
  ResponseEntity<GeneralResponse<ClassResponseDto>> updateClass(@Valid @RequestBody ClassUpdateRequestDto classUpdateRequestDto);

  @Operation(
      summary = "Delete a class",
      description = "Delete a class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Class deleted successfully",
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
  @DeleteMapping("/delete/{classCode}")
  ResponseEntity<GeneralResponse<Void>> deleteClass(@NotEmpty(message = "Class code is required") @PathVariable("classCode") String classCode);

  @Operation(
      summary = "Get class by code",
      description = "Get class by code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Class retrieved successfully",
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
  @GetMapping("/{classCode}")
  ResponseEntity<GeneralResponse<ClassResponseDto>> getClassByCode(@NotEmpty(message = "Class code is required") @PathVariable("classCode") String classCode);
}
