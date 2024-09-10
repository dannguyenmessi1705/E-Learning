package com.didan.elearning.attendances.controller;

import com.didan.elearning.attendances.dto.error.ErrorDto;
import com.didan.elearning.attendances.dto.request.RecordUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.GeneralResponse;
import com.didan.elearning.attendances.dto.response.RecordResponseDto;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/records")
@Tag(
    name = "RecordController",
    description = "The API for managing attendance records"
)
@Validated
public interface IRecordController {
  @Operation(
      summary = "Update record for attendance",
      description = "Update the record for an attendance",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Record updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PutMapping("/update/{recordId}")
  ResponseEntity<GeneralResponse<RecordResponseDto>> updateRecordForAttendance(@NotBlank(message = "Record ID is required") @PathVariable("recordId") String recordId, @Valid @RequestBody RecordUpdateRequestDto recordUpdateRequestDto);

  @Operation(
      summary = "Delete record for attendance",
      description = "Delete the record for an attendance",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Record deleted successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @DeleteMapping("/delete/{recordId}")
  ResponseEntity<GeneralResponse<Void>> deleteRecordForAttendance(@NotBlank(message = "Record ID is required") @PathVariable("recordId") String recordId);

  @Operation(
      summary = "Get record details for attendance",
      description = "Get the details of the record for an attendance",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Record details retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @GetMapping("/details/{recordId}")
  ResponseEntity<GeneralResponse<RecordResponseDto>> getRecordDetailsForAttendance(@NotBlank(message = "Record ID is required") @PathVariable("recordId") String recordId);

  @Operation(
      summary = "Get all records for attendance",
      description = "Get all the records for an attendance",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Records retrieved successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @GetMapping("/all/{attendanceId}")
  ResponseEntity<GeneralResponse<List<RecordResponseDto>>> getAllRecordsForAttendance(@NotBlank(message = "Attendance ID is required") @PathVariable("attendanceId") String attendanceId);
}
