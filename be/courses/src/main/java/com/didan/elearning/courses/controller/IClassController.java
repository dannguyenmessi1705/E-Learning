package com.didan.elearning.courses.controller;

import com.didan.elearning.courses.dto.error.ErrorDto;
import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
  @PostMapping("/create")
  ResponseEntity<MappingJacksonValue> createClass(@Valid @RequestBody ClassRequestDto classRequestDto);

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
  ResponseEntity<MappingJacksonValue> getClassesOfCourse(String courseCode);
}
