package com.didan.elearning.times_table.controller;

import com.didan.elearning.times_table.dto.error.ErrorDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import com.didan.elearning.times_table.dto.response.WeekSchedulesResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/week-schedules")
@Validated
@Tag(
    name = "Week Schedules",
    description = "APIs for managing week schedules"
)
public interface IWeekSchedulesController {
  @Operation(
      summary = "Create week schedules",
      description = "Create week schedules for a semester",
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
  ResponseEntity<GeneralResponse<List<WeekSchedulesResponseDto>>> createWeekSchedules(String semesterCode);

  @Operation(
      summary = "Get week schedules details",
      description = "Get details of a week schedules",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Week schedules details retrieved successfully",
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
  @GetMapping("/get/{weekSchedulesId}")
  ResponseEntity<GeneralResponse<WeekSchedulesResponseDto>> getWeekSchedulesDetails(
      @NotBlank(message = "Week schedules ID is required") @PathVariable("weekSchedulesId") String weekSchedulesId
  );

  @Operation(
      summary = "Get all week schedules",
      description = "Get all week schedules for a semester",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Week schedules retrieved successfully",
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
  @GetMapping("/get-all/{semesterCode}")
  ResponseEntity<GeneralResponse<List<WeekSchedulesResponseDto>>> getAllWeekSchedules(
      @NotBlank(message = "Semester code is required") @PathVariable("semesterCode") String semesterCode
  );

  @Operation(
      summary = "Delete all week schedules",
      description = "Delete all week schedules for a semester",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Week schedules deleted successfully",
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
  @DeleteMapping("/delete-all/{semesterCode}")
  ResponseEntity<GeneralResponse<Void>> deleteAllWeekSchedules(
      @NotBlank(message = "Semester code is required") @PathVariable("semesterCode") String semesterCode
  );
}
