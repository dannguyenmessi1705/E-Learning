package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.CourseClasses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassesRepository extends JpaRepository<CourseClasses, String> {
  List<CourseClasses> findCourseClassesByCourse_CourseCodeIgnoreCase(String courseCode);
  Boolean existsByClassCodeIgnoreCase(String classCode);
}
