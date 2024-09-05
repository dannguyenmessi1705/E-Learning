package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.UserNotifications;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationsRepository extends JpaRepository<UserNotifications, String> {
  List<UserNotifications> findUserNotificationsByUser_UserId(String userId);
}
