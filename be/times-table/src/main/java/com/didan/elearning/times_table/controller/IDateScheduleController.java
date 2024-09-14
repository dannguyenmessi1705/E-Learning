package com.didan.elearning.times_table.controller;

import com.didan.elearning.times_table.dto.error.ErrorDto;
import com.didan.elearning.times_table.dto.request.DateSchedulesFindRequestDto;
import com.didan.elearning.times_table.dto.request.DateSchedulesUpdateRequestDto;
import com.didan.elearning.times_table.dto.response.DateSchedulesResponseDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("${spring.application.name}/v1/date-schedules")
@Validated
@Tag(
    name = "Date Schedules",
    description = "APIs for managing date schedules"
)
public interface IDateScheduleController {
  @Operation(
      summary = "Get date schedules by week schedule",
      description = "Get date schedules by week schedule",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get date schedules by week schedule",
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
  @PostMapping("/get")
  ResponseEntity<GeneralResponse<List<DateSchedulesResponseDto>>> getDateSchedulesByWeekScheduleId(
      @Valid @RequestBody DateSchedulesFindRequestDto dateSchedulesFindRequestDto
  );

  @Operation(
      summary = "Get date schedules by date",
      description = "Get date schedules by date",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get date schedules by date",
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
  @GetMapping("/get")
  ResponseEntity<GeneralResponse<DateSchedulesResponseDto>> getDateSchedulesByDate(
      @NotBlank(message = "Date is required") @Pattern(
          regexp = "^\\d{4}-\\d{2}-\\d{2}$",
          message = "Date format must be yyyy-MM-dd"
      ) @RequestParam("date") String date
  );

  @Operation(
      summary = "Update date schedules",
      description = "Update date schedules",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Update date schedules",
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
  @PutMapping("/update")
  ResponseEntity<GeneralResponse<DateSchedulesResponseDto>> updateDateSchedules(
      @Valid @RequestBody DateSchedulesUpdateRequestDto dateSchedulesUpdateRequestDto
  );

  @Operation(
      summary = "Delete date schedules",
      description = "Delete date schedules",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete date schedules",
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
  @DeleteMapping("/delete")
  ResponseEntity<GeneralResponse<Void>> deleteDateSchedules(
      @NotBlank(message = "Date is required") @Pattern(
          regexp = "^\\d{4}-\\d{2}-\\d{2}$",
          message = "Date format must be yyyy-MM-dd"
      ) @RequestParam("date") String date
  );
}
