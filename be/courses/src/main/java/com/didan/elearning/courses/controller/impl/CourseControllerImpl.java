package com.didan.elearning.courses.controller.impl;

import com.didan.elearning.courses.controller.ICourseController;
import com.didan.elearning.courses.dto.request.CourseCreateRequestDto;
import com.didan.elearning.courses.dto.response.CourseResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.service.ICourseService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@Slf4j
public class CourseControllerImpl implements ICourseController {
  private final ICourseService courseService;
  @Override
  public ResponseEntity<GeneralResponse<CourseResponseDto>> createCourse(
      CourseCreateRequestDto courseCreateRequestDto) {
    log.info("Creating a new course...");
    CourseResponseDto courseResponseDto = courseService.createCourse(courseCreateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Course created successfully", courseResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<CourseResponseDto>>> getAllCourses() {
    log.info("Getting all courses...");
    List<CourseResponseDto> courseResponseDtos = courseService.getAllCourses();
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Returning all courses", courseResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<CourseResponseDto>>> getCoursesOfSemester(
      String semesterCode) {
    log.info("Getting courses of semester {}...", semesterCode);
    List<CourseResponseDto> courseResponseDtos = courseService.getCoursesOfSemester(semesterCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Returning courses of semester " + semesterCode, courseResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<CourseResponseDto>> getCourseByCode(String courseCode) {
    log.info("Getting course with code {}...", courseCode);
    CourseResponseDto courseResponseDto = courseService.getCourseByCode(courseCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Returning course with code " + courseCode, courseResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> assignCourseToSemester(String courseCode,
      String semesterCode) {
    log.info("Assigning course {} to semester {}...", courseCode, semesterCode);
    courseService.assignCourseToSemester(courseCode, semesterCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Course " + courseCode + " assigned to semester " + semesterCode, "Course assigned successfully"), HttpStatus.OK);
  }
}
