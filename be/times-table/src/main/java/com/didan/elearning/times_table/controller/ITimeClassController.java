package com.didan.elearning.times_table.controller;

import com.didan.elearning.times_table.dto.error.ErrorDto;
import com.didan.elearning.times_table.dto.request.TimeClassRequestDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import com.didan.elearning.times_table.dto.response.TimeClassResponseDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/time-class")
@Validated
@Tag(
    name = "Time Class",
    description = "APIs for managing time class"
)
public interface ITimeClassController {
  @Operation(
      summary = "Create a new time class",
      description = "Create a new time class",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Time class created successfully",
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
  ResponseEntity<GeneralResponse<TimeClassResponseDto>> createTimeClass(
      @Valid @RequestBody TimeClassRequestDto timeClassRequestDto
  );

  @Operation(
      summary = "Update a time class",
      description = "Update a time class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Time class updated successfully",
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
  @PutMapping("/update/{timeClassId}")
  ResponseEntity<GeneralResponse<TimeClassResponseDto>> updateTimeClass(
      @NotBlank(message = "Time class ID is required") @PathVariable("timeClassId") String timeClassId,
      @Valid @RequestBody TimeClassRequestDto timeClassRequestDto
  );

  @Operation(
      summary = "Get a time class by ID",
      description = "Get a time class by ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Time class retrieved successfully",
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
  @GetMapping("/get/{timeClassId}")
  ResponseEntity<GeneralResponse<TimeClassResponseDto>> getTimeClass(
      @NotBlank(message = "Time class ID is required") @PathVariable("timeClassId") String timeClassId
  );

  @Operation(
      summary = "Get time classes by class code",
      description = "Get time classes by class code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Time classes retrieved successfully",
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
  @GetMapping("/get/class/{classCode}")
  ResponseEntity<GeneralResponse<List<TimeClassResponseDto>>> getTimeClassesByClassCode(
      @NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode
  );

  @Operation(
      summary = "Get time classes by instructor ID",
      description = "Get time classes by instructor ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Time classes retrieved successfully",
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
  @GetMapping("/get/instructor/{instructorId}")
  ResponseEntity<GeneralResponse<List<TimeClassResponseDto>>> getTimeClassesByInstructorId(
      @NotBlank(message = "Instructor ID is required") @PathVariable("instructorId") String instructorId
  );

  @Operation(
      summary = "Get time classes by course code",
      description = "Get time classes by course code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Time classes retrieved successfully",
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
  @GetMapping("/get/course/{courseCode}")
  ResponseEntity<GeneralResponse<List<TimeClassResponseDto>>> getTimeClassesByCourseCode(
      @NotBlank(message = "Course code is required") @PathVariable("courseCode") String courseCode
  );

  @Operation(
      summary = "Delete a time class",
      description = "Delete a time class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Time class deleted successfully",
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
  @GetMapping("/delete/{timeClassId}")
  ResponseEntity<GeneralResponse<Void>> deleteTimeClass(
      @NotBlank(message = "Time class ID is required") @PathVariable("timeClassId") String timeClassId
  );
}
