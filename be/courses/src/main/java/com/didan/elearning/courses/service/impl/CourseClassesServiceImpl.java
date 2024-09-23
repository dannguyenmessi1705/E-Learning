package com.didan.elearning.courses.service.impl;

import com.didan.elearning.courses.constants.MessageConstants;
import com.didan.elearning.courses.constants.MessageConstants.Status;
import com.didan.elearning.courses.constants.RoleConstants;
import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.request.ClassUpdateRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.RoleResponseDto;
import com.didan.elearning.courses.entity.Course;
import com.didan.elearning.courses.entity.CourseClasses;
import com.didan.elearning.courses.exception.ResourceNotFoundException;
import com.didan.elearning.courses.repository.CourseClassesRepository;
import com.didan.elearning.courses.repository.CourseRepository;
import com.didan.elearning.courses.service.IClientUserService;
import com.didan.elearning.courses.service.ICourseClassesService;
import com.didan.elearning.courses.service.client.UsersFeignClient;
import com.didan.elearning.courses.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class CourseClassesServiceImpl implements ICourseClassesService {
  private final CourseClassesRepository courseClassesRepository;
  private final CourseRepository courseRepository;
  private final IClientUserService clientUserService;

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
    return mappedListCourseCode(courseClasses);
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
    clientUserService.validateInstruction(classRequestDto.getInstructorId());
    if (StringUtils.hasText(classRequestDto.getAssistantId())) {
      clientUserService.validateAssistant(classRequestDto.getAssistantId());
    }
    newCourse.setClassCode(classCode);
    newCourse.setCourse(course);
    newCourse.setClassName(course.getCourseName() + " - " + classCode.split("-")[1]);
    courseClassesRepository.save(newCourse);
    ClassResponseDto response = MapperUtils.map(newCourse, ClassResponseDto.class);
    response.setInstructor(clientUserService.getUserDetail(newCourse.getInstructorId()));
    if (StringUtils.hasText(newCourse.getAssistantId())) {
      response.setAssistant(clientUserService.getUserDetail(newCourse.getAssistantId()));
    }
    response.setCourseCode(course.getCourseCode());
    return response;
  }

  @Override
  public List<ClassResponseDto> getAllClassesByInstructor(String instructorId) {
    List<CourseClasses> courseClasses = courseClassesRepository.findCourseClassesByInstructorIdIgnoreCase(instructorId);
    if (courseClasses.isEmpty()) {
      log.error("No classes found for instructor with ID {}", instructorId);
      return List.of();
    }
    return mappedListCourseCode(courseClasses);
  }

  @Override
  public List<ClassResponseDto> getAllClassesByAssistant(String assistantId) {
    List<CourseClasses> courseClasses = courseClassesRepository.findCourseClassesByAssistantIdIgnoreCase(assistantId);
    if (courseClasses.isEmpty()) {
      log.error("No classes found for assistant with ID {}", assistantId);
      return List.of();
    }
    return mappedListCourseCode(courseClasses);
  }

  @Override
  public ClassResponseDto getClassByCode(String classCode) {
    CourseClasses courseClass = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classCode)
        .orElseThrow(() -> {
          log.error("Class with code {} not found", classCode);
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classCode));
        });
    return mappedCourseCode(courseClass);
  }

  @Override
  @Transactional
  public ClassResponseDto updateCourseClasses(ClassUpdateRequestDto classUpdateRequestDto) {
    CourseClasses courseClass = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classUpdateRequestDto.getClassCode())
        .orElseThrow(() -> {
          log.error("Class with code {} not found", classUpdateRequestDto.getClassCode());
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classUpdateRequestDto.getClassCode()));
        });
    if (StringUtils.hasText(classUpdateRequestDto.getInstructorId())) {
      clientUserService.validateInstruction(classUpdateRequestDto.getInstructorId());
    }
    if (StringUtils.hasText(classUpdateRequestDto.getAssistantId())) {
      clientUserService.validateAssistant(classUpdateRequestDto.getAssistantId());
    }
    if (StringUtils.hasText(classUpdateRequestDto.getClassName())) {
      courseClass.setClassName(classUpdateRequestDto.getClassName());
    }
    if (classUpdateRequestDto.getCapacity() != null) {
      courseClass.setCapacity(classUpdateRequestDto.getCapacity());
    }
    if (classUpdateRequestDto.getClassCode() != null) {
      Course course = courseRepository.findCourseByCourseCodeIgnoreCase(classUpdateRequestDto.getCourseCode())
          .orElseThrow(() -> {
            log.error("Course with code {} not found", classUpdateRequestDto.getCourseCode());
            return new ResourceNotFoundException(String.format(MessageConstants.COURSE_NOT_FOUND, classUpdateRequestDto.getCourseCode()));
          });
      courseClass.setCourse(course);
    }
    courseClassesRepository.save(courseClass);
    log.info("Class with code {} updated successfully", classUpdateRequestDto.getClassCode());
    return mappedCourseCode(courseClass);
  }

  @Override
  @Transactional
  public void deleteCourseClasses(String classCode) {
    CourseClasses courseClass = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classCode)
        .orElseThrow(() -> {
          log.error("Class with code {} not found", classCode);
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classCode));
        });
    courseClassesRepository.delete(courseClass);
    log.info("Class with code {} deleted successfully", classCode);
  }

  @Override
  public boolean checkIfClassExists(String classCode, String courseCode, String semesterCode) {
    CourseClasses classes = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classCode)
        .orElseThrow(() -> {
          log.error("Class with code {} not found", classCode);
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classCode));
        });

    if (Objects.equals(classes.getCourse().getCourseCode(), courseCode)) {
      if (classes.getCourse().getSemestersCourses().stream().anyMatch(semesterCourse -> Objects.equals(semesterCourse.getSemester().getSemesterCode(), semesterCode))) {
        return true;
      } else {
        log.error("Class with code {} not found with course code {} in semester with code {}", classCode, courseCode, semesterCode);
        return false;
      }
    }
    return false;
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
    return switch (field) {
      case "classCode" -> courseClassesRepository.existsByClassCodeIgnoreCase(value);
      case "courseCode" -> courseRepository.existsByCourseCodeIgnoreCase(value);
      default -> false;
    };
  }

  private List<ClassResponseDto> mappedListCourseCode(List<CourseClasses> courseClasses) {
    List<ClassResponseDto> responses = new ArrayList<>();
    courseClasses.forEach(courseClass -> {
      ClassResponseDto response = MapperUtils.map(courseClass, ClassResponseDto.class);
      response.setCourseCode(courseClass.getCourse().getCourseCode());
      response.setInstructor(clientUserService.getUserDetail(courseClass.getInstructorId()));
      if (StringUtils.hasText(courseClass.getAssistantId())) {
        response.setAssistant(clientUserService.getUserDetail(courseClass.getAssistantId()));
      }
      responses.add(response);
    });
    return responses;
  }

  private ClassResponseDto mappedCourseCode(CourseClasses courseClass) {
    ClassResponseDto classResponseDto = MapperUtils.map(courseClass, ClassResponseDto.class);
    classResponseDto.setCourseCode(courseClass.getCourse().getCourseCode());
    classResponseDto.setInstructor(clientUserService.getUserDetail(courseClass.getInstructorId()));
    if (StringUtils.hasText(courseClass.getAssistantId())) {
      classResponseDto.setAssistant(clientUserService.getUserDetail(courseClass.getAssistantId()));
    }
    return classResponseDto;
  }
}
