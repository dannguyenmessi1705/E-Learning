package com.didan.elearning.courses.controller.impl;

import com.didan.elearning.courses.controller.IClassStudentController;
import com.didan.elearning.courses.dto.request.ClassStudentRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.service.IClassStudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ClassStudentControllerImpl implements IClassStudentController {
  private final IClassStudentService classStudentService;
  @Override
  public ResponseEntity<GeneralResponse<String>> addStudentsToClass(
      ClassStudentRequestDto classStudentRequestDto) {
    log.info("Adding students to class {}", classStudentRequestDto.getClassCode());
    classStudentService.addStudentsToClass(classStudentRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Students added to class successfully", null), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> removeStudentsFromClass(
      ClassStudentRequestDto classStudentRequestDto) {
    log.info("Removing students from class {}", classStudentRequestDto.getClassCode());
    classStudentService.removeStudentsFromClass(classStudentRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Students removed from class successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> removeAllStudentsFromClass(String classCode) {
    log.info("Removing all students from class {}", classCode);
    classStudentService.removeAllStudentsFromClass(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "All students removed from class successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> removeAllClassesOfStudent(String studentCode) {
    log.info("Removing all classes of student {}", studentCode);
    classStudentService.removeAllClassesOfStudent(studentCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "All classes removed from student successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getAllClassesOfStudent(
      String studentCode) {
    log.info("Getting all classes of student {}", studentCode);
    List<ClassResponseDto> classResponseDtos = classStudentService.getClassesOfStudent(studentCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Classes of student retrieved successfully", classResponseDtos), HttpStatus.OK);
  }
}
