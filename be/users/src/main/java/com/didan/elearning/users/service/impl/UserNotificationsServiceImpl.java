package com.didan.elearning.users.service.impl;

import com.didan.elearning.users.constant.CheckBoolean;
import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.dto.request.CreateNotificationRequestDto;
import com.didan.elearning.users.dto.response.NotificationResponseDto;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.entity.UserNotifications;
import com.didan.elearning.users.exception.ResourceNotFoundException;
import com.didan.elearning.users.mapper.Mapper;
import com.didan.elearning.users.repository.UserNotificationsRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.service.IUserNotificationsService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserNotificationsServiceImpl implements IUserNotificationsService {
  private final UserRepository userRepository;
  private final UserNotificationsRepository userNotificationsRepository;
  @Override
  public NotificationResponseDto createNotification(CreateNotificationRequestDto notificationRequestDto) {
    User user = userRepository.findById(notificationRequestDto.getUserId()).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND + " - {}", notificationRequestDto.getUserId());
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND + " - " + notificationRequestDto.getUserId());
    });
    UserNotifications notification = new UserNotifications();
    notification.setUser(user);
    notification.setMessage(notificationRequestDto.getMessage());
    userNotificationsRepository.save(notification);
    NotificationResponseDto response = Mapper.map(notification, NotificationResponseDto.class);
    response.setUserId(notificationRequestDto.getUserId());
    log.info(MessageConstant.NOTIFICATION_CREATED_SUCCESSFULLY + ": {}", response);
    return response;
  }

  @Override
  public NotificationResponseDto markAsRead(String notificationId) {
    UserNotifications notification = userNotificationsRepository.findById(notificationId).orElseThrow(() -> {
      log.info(MessageConstant.NOTIFICATION_NOT_FOUND + " - {}", notificationId);
      return new ResourceNotFoundException(MessageConstant.NOTIFICATION_NOT_FOUND + " - " + notificationId);
    });
    notification.setIsRead(CheckBoolean.TRUE);
    userNotificationsRepository.save(notification);
    NotificationResponseDto response = Mapper.map(notification, NotificationResponseDto.class);
    response.setUserId(notification.getUser().getUserId());
    log.info(MessageConstant.NOTIFICATION_MARKED_AS_READ + " - {}", notificationId);
    return response;
  }

  @Override
  public void markAllAsRead(String userId) {
    userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND + " - {}", userId);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND + " - " + userId);
    });
    userNotificationsRepository.findUserNotificationsByUser_UserId(userId).forEach(notification -> {
      notification.setIsRead(CheckBoolean.TRUE);
      userNotificationsRepository.save(notification);
    });
  }

  @Override
  public void deleteNotification(String notificationId) {
    userNotificationsRepository.findById(notificationId).ifPresent(notification -> {
      userNotificationsRepository.delete(notification);
      log.info(MessageConstant.NOTIFICATION_DELETED_SUCCESSFULLY + " - {}", notificationId);
    });
  }

  @Override
  public void deleteAllNotifications(String userId) {
    userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND + " - {}", userId);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND + " - " + userId);
    });
    userNotificationsRepository.findUserNotificationsByUser_UserId(userId).forEach(notification -> {
      userNotificationsRepository.delete(notification);
      log.info(MessageConstant.NOTIFICATION_DELETED_SUCCESSFULLY + " - {}", notification.getNotificationId());
    });
  }

  @Override
  public List<NotificationResponseDto> getNotifications(String userId) {
    userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND + " - {}", userId);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND + " - " + userId);
    });
    List<UserNotifications> notifications = userNotificationsRepository.findUserNotificationsByUser_UserId(userId);
    return Mapper.mapList(notifications, NotificationResponseDto.class);
  }

  @Override
  public void getUnreadNotifications(String userId) {

  }

  @Override
  public void getReadNotifications(String userId) {

  }
}
