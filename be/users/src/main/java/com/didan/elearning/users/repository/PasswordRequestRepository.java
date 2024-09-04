package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.PasswordRequest;
import com.didan.elearning.users.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRequestRepository extends JpaRepository<PasswordRequest, String> {
  Optional<PasswordRequest> findPasswordRequestByUser_UserId(String userId);
  Optional<PasswordRequest> findPasswordRequestByToken(String token);
}
