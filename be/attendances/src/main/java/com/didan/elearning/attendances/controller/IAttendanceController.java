package com.didan.elearning.attendances.controller;

import com.didan.elearning.attendances.dto.error.ErrorDto;
import com.didan.elearning.attendances.dto.request.AttendanceRequestDto;
import com.didan.elearning.attendances.dto.request.AttendanceUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.AttendanceResponseDto;
import com.didan.elearning.attendances.dto.response.GeneralResponse;
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

@Validated
@RequestMapping("${spring.application.name}/v1/attendances")
@Tag(
    name = "AttendanceController",
    description = "The API for managing attendance"
)
public interface IAttendanceController {
  @Operation(
      summary = "Create attendance for class",
      description = "Create attendance for a class",
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
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<AttendanceResponseDto>> createAttendanceForClass(@Valid @RequestBody AttendanceRequestDto attendanceRequestDto);

  @Operation(
      summary = "Get all attendances of a class",
      description = "Get all attendances of a class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Attendances retrieved successfully",
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
  @GetMapping("/all/{classCode}")
  ResponseEntity<GeneralResponse<List<AttendanceResponseDto>>> getAllAttendancesForClass(@NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode);

  @Operation(
      summary = "Get attendance details",
      description = "Get attendance details",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Attendance details retrieved successfully",
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
  @GetMapping("/details/{attendanceId}")
  ResponseEntity<GeneralResponse<AttendanceResponseDto>> getAttendanceDetailsForClass(@NotBlank(message = "Attendance Id is required") @PathVariable("attendanceId") String attendanceId);

  @Operation(
      summary = "Update attendance",
      description = "Update attendance",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Attendance updated successfully",
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
  @PutMapping("/update/{attendanceId}")
  ResponseEntity<GeneralResponse<AttendanceResponseDto>> updateAttendanceForClass(@NotBlank(message = "Attendance Id is required") @PathVariable("attendanceId") String attendanceId, @RequestBody AttendanceUpdateRequestDto attendanceUpdateRequestDto);

  @Operation(
      summary = "Delete attendance",
      description = "Delete attendance",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Attendance deleted successfully",
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
  @DeleteMapping("/delete/{attendanceId}")
  ResponseEntity<GeneralResponse<Void>> deleteAttendanceForClass(@NotBlank(message = "Attendance Id is required") @PathVariable("attendanceId") String attendanceId);
}
