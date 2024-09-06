package com.didan.elearning.users.controller;

import com.didan.elearning.users.dto.error.ErrorDto;
import com.didan.elearning.users.dto.request.CreateNotificationRequestDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.NotificationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/notification")
@Tag(
    name = "User Notifications API",
    description = "The API for user notifications"
)
public interface IUserNotificationsController {
  @Operation(
      summary = "Create a new notification",
      description = "Create a new notification with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Created notification successfully",
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
  ResponseEntity<GeneralResponse<NotificationResponseDto>> createNotification(@Valid @RequestBody
      CreateNotificationRequestDto createNotificationRequestDto);

  @Operation(
      summary = "Read a notification",
      description = "Read a notification with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Read notification successfully",
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
  @PatchMapping("/read/{notificationId}")
  ResponseEntity<GeneralResponse<NotificationResponseDto>> readNotification(@NotBlank @PathVariable("notificationId") String notificationId);

  @Operation(
      summary = "Read all notifications",
      description = "Read all notifications with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Read all notifications successfully",
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
  @PatchMapping("/read/all/{userId}")
  ResponseEntity<GeneralResponse<String>> readAllNotifications(@NotBlank @PathVariable("userId") String userId);

  @Operation(
      summary = "Delete a notification",
      description = "Delete a notification with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete notification successfully",
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
  @DeleteMapping("/delete/{notificationId}")
  ResponseEntity<GeneralResponse<String>> deleteNotification(@NotBlank @PathVariable("notificationId") String notificationId);

  @Operation(
      summary = "Delete all notifications",
      description = "Delete all notifications with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete all notifications successfully",
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
  @DeleteMapping("/delete/all/{userId}")
  ResponseEntity<GeneralResponse<String>> deleteAllNotifications(@NotBlank @PathVariable("userId") String userId);

  @Operation(
      summary = "Get all notifications",
      description = "Get all notifications with the provided information",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get all notifications successfully",
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
  @GetMapping("/get/{userId}")
  ResponseEntity<GeneralResponse<List<NotificationResponseDto>>> getNotifications(@NotBlank @PathVariable("userId") String userId);
}
