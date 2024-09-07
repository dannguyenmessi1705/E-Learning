package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.SemestersCourses;
import com.didan.elearning.courses.entity.key.SemestersCoursesId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestersCoursesRepository extends JpaRepository<SemestersCourses, SemestersCoursesId> {
  List<SemestersCourses> findSemestersCoursesBySemester_SemesterCode(String semesterCode);
  List<SemestersCourses> findSemestersCoursesByCourse_CourseCode(String courseCode);
  Optional<SemestersCourses> findSemestersCoursesBySemester_SemesterCodeAndCourse_CourseCode(String semesterCode, String courseCode);
}
