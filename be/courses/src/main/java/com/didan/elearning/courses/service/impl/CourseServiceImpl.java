package com.didan.elearning.courses.service.impl;

import com.didan.elearning.courses.constants.MessageConstants;
import com.didan.elearning.courses.dto.request.CourseCreateRequestDto;
import com.didan.elearning.courses.dto.response.CourseResponseDto;
import com.didan.elearning.courses.entity.Course;
import com.didan.elearning.courses.entity.Semester;
import com.didan.elearning.courses.entity.SemestersCourses;
import com.didan.elearning.courses.entity.key.SemestersCoursesId;
import com.didan.elearning.courses.exception.ResourceAlreadyExistException;
import com.didan.elearning.courses.exception.ResourceNotFoundException;
import com.didan.elearning.courses.repository.CourseRepository;
import com.didan.elearning.courses.repository.SemesterRepository;
import com.didan.elearning.courses.repository.SemestersCoursesRepository;
import com.didan.elearning.courses.service.ICourseService;
import com.didan.elearning.courses.utils.MapperUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements ICourseService {
  private final CourseRepository courseRepository;
  private final SemesterRepository semesterRepository;
  private final SemestersCoursesRepository semestersCoursesRepository;
  @Override
  public CourseResponseDto createCourse(CourseCreateRequestDto courseCreateRequestDto) {
    courseRepository.findCourseByCourseCodeIgnoreCase(courseCreateRequestDto.getCourseCode())
        .ifPresent(course -> {
          log.info("Course with code {} already exists", courseCreateRequestDto.getCourseCode());
          throw new ResourceAlreadyExistException(String.format(MessageConstants.COURSE_ALREADY_EXISTS, courseCreateRequestDto.getCourseCode()));
        });
    Course course = MapperUtils.map(courseCreateRequestDto, Course.class);
    courseRepository.save(course);
    CourseResponseDto courseResponseDto = MapperUtils.map(course, CourseResponseDto.class);
    log.info("Course created successfully");
    return courseResponseDto;
  }

  @Override
  public List<CourseResponseDto> getAllCourses() {
    List<Course> courses = courseRepository.findAll(
        Sort.by(Sort.Order.asc("courseCode"))
    );
    if (courses.isEmpty()) {
      log.info("No courses found");
      return List.of();
    }
    log.info("Returning all courses");
    return MapperUtils.mapList(courses, CourseResponseDto.class);
  }

  @Override
  public List<CourseResponseDto> getCoursesOfSemester(String semesterCode) {
    semesterRepository.findSemesterBySemesterCodeIgnoreCase(semesterCode)
        .orElseThrow(() -> {
          log.error("Semester with code {} not found", semesterCode);
          return new ResourceNotFoundException(String.format(MessageConstants.SEMESTER_NOT_FOUND, semesterCode));
        });
    List<SemestersCourses> semestersCourses = semestersCoursesRepository.findSemestersCoursesBySemester_SemesterCode(semesterCode);
    if (semestersCourses.isEmpty()) {
      log.info("No courses found for semester with code {}", semesterCode);
      return List.of();
    }
    List<Course> courses = new ArrayList<>();
    for (SemestersCourses semestersCourse : semestersCourses) {
      courses.add(semestersCourse.getCourse());
    }
    log.info("Returning all courses for semester with code {}", semesterCode);
    return MapperUtils.mapList(courses, CourseResponseDto.class);
  }

  @Override
  public CourseResponseDto getCourseByCode(String courseCode) {
    Course course = courseRepository.findCourseByCourseCodeIgnoreCase(courseCode)
        .orElseThrow(() -> {
          log.error("Course with code {} not found", courseCode);
          return new ResourceNotFoundException(String.format(MessageConstants.COURSE_NOT_FOUND, courseCode));
        });
    log.info("Returning course with code {}", courseCode);
    return MapperUtils.map(course, CourseResponseDto.class);
  }

  @Override
  public void assignCourseToSemester(String courseCode, String semesterCode) {
    Course course = courseRepository.findCourseByCourseCodeIgnoreCase(courseCode)
        .orElseThrow(() -> {
          log.error("Course with code {} not found", courseCode);
          return new ResourceNotFoundException(String.format(MessageConstants.COURSE_NOT_FOUND, courseCode));
        });
    Semester semester = semesterRepository.findSemesterBySemesterCodeIgnoreCase(semesterCode)
        .orElseThrow(() -> {
          log.error("Semester with code {} not found", semesterCode);
          return new ResourceNotFoundException(String.format(MessageConstants.SEMESTER_NOT_FOUND, semesterCode));
        });
    semestersCoursesRepository.findSemestersCoursesBySemester_SemesterCodeAndCourse_CourseCode(semesterCode, courseCode)
        .ifPresent(semestersCourses -> {
          log.info("Course with code {} already assigned to semester with code {}", courseCode, semesterCode);
          throw new ResourceAlreadyExistException("Course already assigned to semester");
        });
    SemestersCourses semestersCourses = new SemestersCourses();
    semestersCourses.setSemestersCoursesId(new SemestersCoursesId(semester.getSemesterId(), course.getCourseId()));
    semestersCoursesRepository.save(semestersCourses);
    log.info("Course with code {} assigned to semester with code {}", courseCode, semesterCode);
  }
}
