package com.didan.elearning.grades.controller.impl;

import com.didan.elearning.grades.controller.IGradeClassController;
import com.didan.elearning.grades.dto.request.GradeClassRequestDto;
import com.didan.elearning.grades.dto.response.GeneralResponse;
import com.didan.elearning.grades.dto.response.GradeClassResponseDto;
import com.didan.elearning.grades.service.IGradeClassService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class GradeClassControllerImpl implements IGradeClassController {
  private final IGradeClassService gradeClassService;
  @Override
  public ResponseEntity<GeneralResponse<GradeClassResponseDto>> createGradeClass(
      GradeClassRequestDto gradeClassRequestDto) {
    log.info("=======Create Grade Class=======");
    GradeClassResponseDto gradeClassResponseDto = gradeClassService.createGradeClass(gradeClassRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Grade class created successfully", gradeClassResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<GradeClassResponseDto>> updateGradeClass(
      String gradeClassId, GradeClassRequestDto gradeClassRequestDto) {
    log.info("=======Update Grade Class=======");
    GradeClassResponseDto gradeClassResponseDto = gradeClassService.updateGradeClass(gradeClassId, gradeClassRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Grade class updated successfully", gradeClassResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<GradeClassResponseDto>> getGradeByClassCode(
      String classCode) {
    log.info("=======Get Grade By Class Code=======");
    GradeClassResponseDto gradeClassResponseDto = gradeClassService.getGradeByClassCode(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Grade class retrieved successfully", gradeClassResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteGradeClass(String gradeClassId) {
    log.info("=======Delete Grade Class=======");
    gradeClassService.deleteGradeClass(gradeClassId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Grade class deleted successfully", null), HttpStatus.NO_CONTENT);
  }
}
