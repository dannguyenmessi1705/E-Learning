package com.didan.elearning.times_table.repository;

import com.didan.elearning.times_table.entity.TimeClasses;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeClassRepository extends JpaRepository<TimeClasses, String> {
  Optional<TimeClasses> findTimeClassesByCourseCodeIgnoreCaseAndClassCodeIgnoreCaseAndInstructorIdIgnoreCase(String courseCode, String classCode, String instructorId);
  List<TimeClasses> findTimeClassesByCourseCodeIgnoreCase(String courseCode);
  List<TimeClasses> findTimeClassesByClassCodeIgnoreCase(String classCode);
  List<TimeClasses> findTimeClassesByInstructorIdIgnoreCase(String instructorId);
}
