package com.didan.elearning.courses.controller.impl;

import com.didan.elearning.courses.controller.ISemesterController;
import com.didan.elearning.courses.dto.request.SemesterRequestDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.SemesterResponseDto;
import com.didan.elearning.courses.service.ISemesterService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class SemesterControllerImpl implements ISemesterController {
  private final ISemesterService semesterService;
  @Override
  public ResponseEntity<GeneralResponse<SemesterResponseDto>> createSemester(
      SemesterRequestDto semesterRequestDto) {
    log.info("Creating semester...");
    SemesterResponseDto semesterResponseDto = semesterService.createSemester(semesterRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Semester created successfully", semesterResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<SemesterResponseDto>>> getAllSemesters() {
    log.info("Getting all semesters...");
    List<SemesterResponseDto> semesters = semesterService.getAllSemesters();
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Returning all semesters", semesters), HttpStatus.OK);
  }
}
