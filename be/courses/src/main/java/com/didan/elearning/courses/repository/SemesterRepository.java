package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.Semester;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, String> {
  Optional<Semester> findSemesterBySemesterCodeIgnoreCase(String semesterCode);
}
