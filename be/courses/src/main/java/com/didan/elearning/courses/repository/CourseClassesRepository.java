package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.CourseClasses;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassesRepository extends JpaRepository<CourseClasses, String> {
  List<CourseClasses> findCourseClassesByCourse_CourseCodeIgnoreCase(String courseCode);
  Boolean existsByClassCodeIgnoreCase(String classCode);
  Optional<CourseClasses> findCourseClassesByClassCodeIgnoreCase(String classCode);
  List<CourseClasses> findCourseClassesByInstructorIdIgnoreCase(String instructorId);
  List<CourseClasses> findCourseClassesByAssistantIdIgnoreCase(String assistantId);

}
