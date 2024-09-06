package com.didan.elearning.users.controller;

import com.didan.elearning.users.dto.error.ErrorDto;
import com.didan.elearning.users.dto.request.ActivityRequestDto;
import com.didan.elearning.users.dto.response.ActivityResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/logs")
@Tag(
    name = "Activity Logs API",
    description = "The API for activity logs"
)
@Validated
public interface IActivityController {
  @Operation(
      summary = "Create a new activity log",
      description = "Create a new activity log with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Created activity log successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<ActivityResponseDto>> createActivity(@RequestHeader("userId") String userId, @Valid @RequestBody ActivityRequestDto activityRequestDto);

  @Operation(
      summary = "Get all activity logs",
      description = "Get all activity logs for the user",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get activity logs successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @GetMapping("/get")
  ResponseEntity<GeneralResponse<List<ActivityResponseDto>>> getActivities(@RequestHeader("userId") String userId);
}
