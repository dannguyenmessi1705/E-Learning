package com.didan.elearning.grades.controller.impl;

import com.didan.elearning.grades.controller.IWeightGradeController;
import com.didan.elearning.grades.dto.request.WeightGradeRequestDto;
import com.didan.elearning.grades.dto.response.GeneralResponse;
import com.didan.elearning.grades.dto.response.WeightGradeResponseDto;
import com.didan.elearning.grades.service.IWeightGradeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class WeightGradeControllerImpl implements IWeightGradeController {
  private final IWeightGradeService weightGradeService;
  @Override
  public ResponseEntity<GeneralResponse<WeightGradeResponseDto>> createWeightGradeForCourse(
      WeightGradeRequestDto weightGradeRequestDto) {
    log.info("==========Creating weight grade for course {}==========", weightGradeRequestDto.getCourseCode());
    WeightGradeResponseDto weightGradeResponseDto = weightGradeService.createWeightGradeForCourse(weightGradeRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Weight grade created successfully", weightGradeResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<WeightGradeResponseDto>> updateWeightGradeForCourse(
      String weightGradeId, WeightGradeRequestDto weightGradeRequestDto) {
    log.info("==========Updating weight grade for course {}==========", weightGradeRequestDto.getCourseCode());
    WeightGradeResponseDto weightGradeResponseDto = weightGradeService.updateWeightGradeForCourse(weightGradeId, weightGradeRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Weight grade updated successfully", weightGradeResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<WeightGradeResponseDto>> getWeightGradeByCourseCode(
      String courseCode) {
    log.info("==========Getting weight grade for course {}==========", courseCode);
    WeightGradeResponseDto weightGradeResponseDto = weightGradeService.getWeightGradeByCourseCode(courseCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Weight grade retrieved successfully", weightGradeResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteWeightGrade(String weightGradeId) {
    log.info("========Deleting weight grade with id {}==========", weightGradeId);
    weightGradeService.deleteWeightGrade(weightGradeId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Weight grade deleted successfully", null), HttpStatus.NO_CONTENT);
  }
}
