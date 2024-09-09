package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
  Optional<Course> findCourseByCourseCodeIgnoreCase(String courseCode);
  Boolean existsByCourseCodeIgnoreCase(String courseCode);
}
