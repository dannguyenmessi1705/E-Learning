package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.controller.IUserNotificationsController;
import com.didan.elearning.users.dto.request.CreateNotificationRequestDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.NotificationResponseDto;
import com.didan.elearning.users.service.IUserNotificationsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Slf4j
@AllArgsConstructor
public class UserNotificationsControllerImpl implements IUserNotificationsController {
  private final IUserNotificationsService userNotificationsService;
  @Override
  public ResponseEntity<GeneralResponse<NotificationResponseDto>> createNotification(
      CreateNotificationRequestDto createNotificationRequestDto) {
    log.info("Creating notification...");
    NotificationResponseDto notification = userNotificationsService.createNotification(createNotificationRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), MessageConstant.NOTIFICATION_CREATED_SUCCESSFULLY, notification), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<NotificationResponseDto>> readNotification(String notificationId) {
    log.info("Reading notification...");
    NotificationResponseDto notification = userNotificationsService.markAsRead(notificationId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), MessageConstant.NOTIFICATION_READ_SUCCESSFULLY, notification), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> readAllNotifications(String userId) {
    log.info("Reading all notifications...");
    userNotificationsService.markAllAsRead(userId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), MessageConstant.NOTIFICATIONS_READ_SUCCESSFULLY, "userId: " + userId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<NotificationResponseDto>> deleteNotification(
      String notificationId) {
    log.info("Deleting notification...");
    userNotificationsService.deleteNotification(notificationId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), MessageConstant.NOTIFICATION_DELETED_SUCCESSFULLY, "notificationId: " + notificationId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> deleteAllNotifications(String userId) {
    return null;
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> getNotifications(String userId) {
    return null;
  }
}
