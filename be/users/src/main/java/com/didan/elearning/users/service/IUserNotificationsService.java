package com.didan.elearning.users.service;

import com.didan.elearning.users.dto.request.CreateNotificationRequestDto;
import com.didan.elearning.users.dto.response.NotificationResponseDto;
import java.util.List;

public interface IUserNotificationsService {
    NotificationResponseDto createNotification(CreateNotificationRequestDto notificationRequestDto);
    NotificationResponseDto markAsRead(String notificationId);
    void markAllAsRead(String userId);
    void deleteNotification(String notificationId);
    void deleteAllNotifications(String userId);
    List<NotificationResponseDto> getNotifications(String userId);
    void getUnreadNotifications(String userId);
    void getReadNotifications(String userId);
}
