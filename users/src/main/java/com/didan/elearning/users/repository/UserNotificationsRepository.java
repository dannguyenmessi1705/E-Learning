package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.UserNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationsRepository extends JpaRepository<UserNotifications, String> {

}
