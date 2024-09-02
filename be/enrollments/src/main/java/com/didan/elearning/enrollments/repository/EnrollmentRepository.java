package com.didan.elearning.enrollments.repository;

import com.didan.elearning.enrollments.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

}
