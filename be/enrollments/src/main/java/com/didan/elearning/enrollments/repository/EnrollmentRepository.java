package com.didan.elearning.enrollments.repository;

import com.didan.elearning.enrollments.entity.Enrollment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
  List<Enrollment> findEnrollmentByStudentCodeIgnoreCase(String studentId);
  List<Enrollment> findEnrollmentByClassRegistrationDetails_ClassDetailsIdIgnoreCase(String classDetailsId);
  List<Enrollment> findEnrollmentByStudentCodeIgnoreCaseAndClassRegistrationDetails_ClassDetailsIdIgnoreCase(String studentId, String classDetailsId);
}
