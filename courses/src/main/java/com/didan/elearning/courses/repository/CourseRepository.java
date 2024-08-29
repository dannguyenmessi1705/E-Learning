package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {

}
