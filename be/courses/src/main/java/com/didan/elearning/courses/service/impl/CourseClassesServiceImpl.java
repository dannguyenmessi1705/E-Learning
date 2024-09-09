package com.didan.elearning.courses.service.impl;

import com.didan.elearning.courses.constants.MessageConstants;
import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.entity.Course;
import com.didan.elearning.courses.entity.CourseClasses;
import com.didan.elearning.courses.exception.ResourceNotFoundException;
import com.didan.elearning.courses.repository.CourseClassesRepository;
import com.didan.elearning.courses.repository.CourseRepository;
import com.didan.elearning.courses.service.ICourseClassesService;
import com.didan.elearning.courses.utils.MapperUtils;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CourseClassesServiceImpl implements ICourseClassesService {
  private final CourseClassesRepository courseClassesRepository;
  private final CourseRepository courseRepository;

  @Override
  public List<ClassResponseDto> getAllClassesOfCourse(String courseCode) {
    courseRepository.findCourseByCourseCodeIgnoreCase(courseCode)
        .orElseThrow(() -> {
          log.error("Course with code {} not found", courseCode);
          return new ResourceNotFoundException(String.format(MessageConstants.COURSE_NOT_FOUND, courseCode));
        });
    List<CourseClasses> courseClasses = courseClassesRepository.findCourseClassesByCourse_CourseCodeIgnoreCase(courseCode);
    if (courseClasses.isEmpty()) {
      log.error("No classes found for course with code {}", courseCode);
      return List.of();
    }
    List<ClassResponseDto> responses = MapperUtils.mapList(courseClasses, ClassResponseDto.class);
    return responses;
  }

  @Override
  public ClassResponseDto createClassOfCourse(ClassRequestDto classRequestDto) {
    Course course = courseRepository.findCourseByCourseCodeIgnoreCase(classRequestDto.getCourseCode())
        .orElseThrow(() -> {
          log.error("Course with code {} not found", classRequestDto.getCourseCode());
          return new ResourceNotFoundException(String.format(MessageConstants.COURSE_NOT_FOUND, classRequestDto.getCourseCode()));
        });
    CourseClasses newCourse = MapperUtils.map(classRequestDto, CourseClasses.class);
    String classCode = setClassCode(classRequestDto.getStudentYear());
    /**
     * OpenFeint to UserService to get instructor and assistant
     * Instructor instructor = userService.getInstructorById(classRequestDto.getInstructorId());
     * Assistant assistant = new Assistant();
     * if (classRequestDto.getAssistantId() != null) {
     * assistant = userService.getAssistantById(classRequestDto.getAssistantId());
     * if (assistant == null) {
     * throw new ResourceNotFoundException("Assistant not found");
     * }
     * }
     * if (instructor == null) {
     *  throw new ResourceNotFoundException("Instructor or assistant not found");
     *  }
     */
    newCourse.setClassCode(classCode);
    newCourse.setCourse(course);
    newCourse.setClassName(course.getCourseName() + " - " + classCode.split("-")[1]);
    courseClassesRepository.save(newCourse);
    ClassResponseDto response = MapperUtils.map(newCourse, ClassResponseDto.class);
    response.setCourseCode(course.getCourseCode());
    return response;
  }

  @Override
  public List<CourseClasses> getAllClassesByInstructor(String instructorId) {
    return List.of();
  }

  @Override
  public List<CourseClasses> getAllClassesByAssistant(String assistantId) {
    return List.of();
  }

  @Override
  public CourseClasses getClassByCode(String classCode) {
    return null;
  }

  @Override
  public void addClassToCourse(String courseCode, CourseClasses courseClasses) {

  }

  @Override
  public void updateCourseClasses(CourseClasses courseClasses) {

  }

  @Override
  public void deleteCourseClasses(String classCode) {

  }

  public String setClassCode(int startYear) {
    int random = (int) (Math.random() * 999) + 1;
    String classCode = "D" + startYear + "-" + String.format("%03d", random);
    while (checkExist("classCode", classCode)) {
      classCode = "D" + startYear + "-" + String.format("%03d", random);
    }
    return classCode;
  }

  public boolean checkExist(String field, String value) {
    switch (field) {
      case "classCode":
        return courseClassesRepository.existsByClassCodeIgnoreCase(value);
      case "courseCode":
        return courseRepository.existsByCourseCodeIgnoreCase(value);
      default:
        return false;
    }
  }
}
