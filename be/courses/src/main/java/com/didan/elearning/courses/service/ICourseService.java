package com.didan.elearning.courses.service;

import com.didan.elearning.courses.dto.request.CourseCreateRequestDto;
import com.didan.elearning.courses.dto.response.CourseResponseDto;
import java.util.List;

public interface ICourseService {
  CourseResponseDto createCourse(CourseCreateRequestDto courseCreateRequestDto);
  List<CourseResponseDto> getAllCourses();
  List<CourseResponseDto> getCoursesOfSemester(String semesterCode);
  CourseResponseDto getCourseByCode(String courseCode);
  void assignCourseToSemester(String courseCode, String semesterCode);
}
