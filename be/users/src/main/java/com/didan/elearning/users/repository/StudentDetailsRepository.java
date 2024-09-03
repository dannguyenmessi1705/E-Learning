package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.StudentDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, String> {
  Optional<StudentDetails> findFirstByUser_UserId(String userId);
}
