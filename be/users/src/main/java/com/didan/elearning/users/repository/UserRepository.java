package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Boolean existsUserByUsername(String username);
  Boolean existsUserByEmail(String email);
  Boolean existsUserByPhoneNumber(String phoneNumber);
  @Modifying
  @Transactional
  void deleteUserByUserId(String userId);
  List<User> findUserByEmailIsContainingIgnoreCaseOrderByFullName(String email);
  List<User> findUserByFullNameIsContainingIgnoreCaseOrderByFullName(String fullName);
  List<User> findUserByStudentDetails_StudentCodeOrderByFullName(String studentCode);
  List<User> findUserByPhoneNumberOrderByFullName(String phoneNumber);
  Optional<User> findUserByEmail(String email);
}
