package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.CourseClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassesRepository extends JpaRepository<CourseClasses, String> {

}
