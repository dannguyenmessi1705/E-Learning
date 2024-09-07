package com.didan.elearning.courses.controller;

import com.didan.elearning.courses.dto.error.ErrorDto;
import com.didan.elearning.courses.dto.request.CourseCreateRequestDto;
import com.didan.elearning.courses.dto.response.CourseResponseDto;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
    name = "Course",
    description = "The course API"
)
@RequestMapping("${spring.application.name}/v1/courses")
@Validated
public interface ICourseController {
  @Operation(
      summary = "Create a new course",
      description = "API to create a new course",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Course created successfully",
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
  ResponseEntity<GeneralResponse<CourseResponseDto>> createCourse(@Valid @RequestBody
  CourseCreateRequestDto courseCreateRequestDto);

  @Operation(
      summary = "Get all courses",
      description = "API to get all courses",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Courses fetched successfully",
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
  ResponseEntity<GeneralResponse<List<CourseResponseDto>>> getAllCourses();

  @Operation(
      summary = "Get all courses of a semester",
      description = "API to get all courses of a semester",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Courses fetched successfully",
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
  @GetMapping("/semester/{semesterCode}")
  ResponseEntity<GeneralResponse<List<CourseResponseDto>>> getCoursesOfSemester(@NotEmpty(message = "Semester code is required") @RequestParam("semesterCode") String semesterCode);

  @Operation(
      summary = "Get course by code",
      description = "API to get course by code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Course fetched successfully",
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
  @GetMapping("/{courseCode}")
  ResponseEntity<GeneralResponse<CourseResponseDto>> getCourseByCode(@NotEmpty(message = "Course code is required") @PathVariable("courseCode") String courseCode);

  @Operation(
      summary = "Assign course to semester",
      description = "API to assign course to semester",
      responses = {
          @ApiResponse(
              responseCode = "200",
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
  @PostMapping("/assign/{courseCode}/{semesterCode}")
  ResponseEntity<GeneralResponse<String>> assignCourseToSemester(@NotEmpty(message = "Course code is required") @RequestParam("courseCode") String courseCode, @NotEmpty(message = "Semester code is required") @RequestParam("semesterCode") String semesterCode);
}
