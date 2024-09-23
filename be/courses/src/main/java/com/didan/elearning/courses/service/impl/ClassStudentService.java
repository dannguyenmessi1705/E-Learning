package com.didan.elearning.courses.service.impl;

import com.didan.elearning.courses.constants.MessageConstants;
import com.didan.elearning.courses.constants.MessageConstants.Status;
import com.didan.elearning.courses.dto.request.ClassStudentRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.courses.entity.ClassStudents;
import com.didan.elearning.courses.entity.CourseClasses;
import com.didan.elearning.courses.exception.ResourceNotFoundException;
import com.didan.elearning.courses.repository.ClassStudentsRepository;
import com.didan.elearning.courses.repository.CourseClassesRepository;
import com.didan.elearning.courses.service.IClassStudentService;
import com.didan.elearning.courses.service.IClientUserService;
import com.didan.elearning.courses.service.client.UsersFeignClient;
import jakarta.transaction.Transactional;
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
public class ClassStudentService implements IClassStudentService {
  private final ClassStudentsRepository classStudentsRepository;
  private final CourseClassesRepository courseClassesRepository;
  private final IClientUserService clientUserService;

  @Override
  public void addStudentsToClass(ClassStudentRequestDto classStudentRequestDto) {
    CourseClasses courseClass = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classStudentRequestDto.getClassCode())
        .orElseThrow(() -> {
          log.info("Class with code {} not found", classStudentRequestDto.getClassCode());
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classStudentRequestDto.getClassCode()));
        });
    String[] studentCodes = classStudentRequestDto.getStudentCodes();
    for (String studentCode : studentCodes) {
      clientUserService.getStudentByStudentCode(studentCode);
      ClassStudents classStudents = ClassStudents.builder()
          .studentCode(studentCode)
          .courseClasses(courseClass)
          .build();
    classStudentsRepository.save(classStudents);
    }
  }

  @Override
  @Transactional
  public void removeStudentsFromClass(ClassStudentRequestDto classStudentRequestDto) {
    CourseClasses courseClass = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classStudentRequestDto.getClassCode())
        .orElseThrow(() -> {
          log.info("Class with code {} not found", classStudentRequestDto.getClassCode());
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classStudentRequestDto.getClassCode()));
        });
    for (String studentCode : classStudentRequestDto.getStudentCodes()) {
      clientUserService.getStudentByStudentCode(studentCode);
      ClassStudents classStudents = classStudentsRepository.findClassStudentsByStudentCodeAndCourseClasses_ClassCode(studentCode, classStudentRequestDto.getClassCode())
          .orElseThrow(() -> {
            log.info("Student with code {} not found in class {}", studentCode, classStudentRequestDto.getClassCode());
            return new ResourceNotFoundException(String.format(MessageConstants.STUDENT_NOT_FOUND_IN_CLASS, studentCode, classStudentRequestDto.getClassCode()));
          });
      classStudentsRepository.delete(classStudents);
    }
  }

  @Override
  public void removeAllStudentsFromClass(String classCode) {
    CourseClasses courseClass = courseClassesRepository.findCourseClassesByClassCodeIgnoreCase(classCode)
        .orElseThrow(() -> {
          log.info("Class with code {} not found", classCode);
          return new ResourceNotFoundException(String.format(MessageConstants.CLASS_NOT_FOUND, classCode));
        });
    classStudentsRepository.deleteAllByCourseClasses_ClassCode(courseClass.getClassCode());
  }

  @Override
  public void removeAllClassesOfStudent(String studentCode) {
    clientUserService.getStudentByStudentCode(studentCode);
    classStudentsRepository.deleteAllByStudentCode(studentCode);
  }

  @Override
  public List<ClassResponseDto> getClassesOfStudent(String studentCode) {
    clientUserService.getStudentByStudentCode(studentCode);
    List<ClassStudents> classStudents = classStudentsRepository.findClassStudentsByStudentCodeIgnoreCase(studentCode);
    if (classStudents.isEmpty()) {
      log.info("No classes found for student with code {}", studentCode);
      return List.of();
    }
    return classStudents.stream()
        .map(classStudent -> ClassResponseDto.builder()
            .classCode(classStudent.getCourseClasses().getClassCode())
            .className(classStudent.getCourseClasses().getClassName())
            .classId(classStudent.getCourseClasses().getClassId())
            .capacity(classStudent.getCourseClasses().getCapacity())
            .instructor(clientUserService.getUserDetail(classStudent.getCourseClasses().getInstructorId()))
            .assistant(StringUtils.hasText(classStudent.getCourseClasses().getAssistantId()) ? clientUserService.getUserDetail(classStudent.getCourseClasses().getAssistantId()) : null)
            .courseCode(classStudent.getCourseClasses().getCourse().getCourseCode())
            .build())
        .toList();
  }

  @Override
  public List<UpdateUserDetailResponseDto> getStudentsInClass(String classCode) {
    List<ClassStudents> classStudents = classStudentsRepository.findClassStudentsByCourseClasses_ClassCode(classCode);
    if (classStudents.isEmpty()) {
      log.info("No students found in class with code {}", classCode);
      return List.of();
    }
    return classStudents.stream()
        .map(classStudent -> clientUserService.getStudentByStudentCode(classStudent.getStudentCode()))
        .toList();
  }
}
