package com.didan.elearning.grades.controller.impl;

import com.didan.elearning.grades.controller.IStudentGradeController;
import com.didan.elearning.grades.dto.request.StudentGradeRequestDto;
import com.didan.elearning.grades.dto.response.GeneralResponse;
import com.didan.elearning.grades.dto.response.StudentGradeResponseDto;
import com.didan.elearning.grades.service.IStudentGradeService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class StudentGradeControllerImpl implements IStudentGradeController {
  private final IStudentGradeService studentGradeService;

  @Override
  public ResponseEntity<GeneralResponse<StudentGradeResponseDto>> createStudentGrade(
      StudentGradeRequestDto studentGradeRequestDto) {
    log.info("=======Create student grade=======");
    StudentGradeResponseDto studentGradeResponseDto = studentGradeService.createStudentGrade(studentGradeRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Create student grade successfully", studentGradeResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<StudentGradeResponseDto>> updateStudentGrade(
      String studentGradeId, StudentGradeRequestDto studentGradeRequestDto) {
    log.info("=======Update student grade=======");
    StudentGradeResponseDto studentGradeResponseDto = studentGradeService.updateStudentGrade(studentGradeId, studentGradeRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Update student grade successfully", studentGradeResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<StudentGradeResponseDto>>> getStudentGradeByStudentCode(
      String studentCode) {
    log.info("=======Get student grade by student code=======");
    List<StudentGradeResponseDto> studentGradeResponseDtos = studentGradeService.getStudentGradeByStudentCode(studentCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get student grade by student code successfully", studentGradeResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<StudentGradeResponseDto>>> getStudentGradeByClassCode(
      String classCode) {
    log.info("=======Get student grade by class code=======");
    List<StudentGradeResponseDto> studentGradeResponseDtos = studentGradeService.getStudentGradeByClassCode(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get student grade by class code successfully", studentGradeResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<StudentGradeResponseDto>> getStudentGradeByClassCodeAndStudentCode(
      String classCode, String studentCode) {
    log.info("=======Get student grade by class code and student code=======");
    StudentGradeResponseDto studentGradeResponseDto = studentGradeService.getGradeByStudentCodeAndClassCode(studentCode, classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get student grade by class code and student code successfully", studentGradeResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteStudentGrade(String studentGradeId) {
    log.info("=======Delete student grade=======");
    studentGradeService.deleteStudentGrade(studentGradeId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Delete student grade successfully", null), HttpStatus.OK);
  }
}
