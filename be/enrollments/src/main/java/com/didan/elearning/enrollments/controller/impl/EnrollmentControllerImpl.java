package com.didan.elearning.enrollments.controller.impl;

import com.didan.elearning.enrollments.controller.IEnrollmentController;
import com.didan.elearning.enrollments.dto.request.EnrollmentCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentFilterRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.EnrollmentResponseDto;
import com.didan.elearning.enrollments.dto.response.GeneralResponse;
import com.didan.elearning.enrollments.service.IEnrollmentService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class EnrollmentControllerImpl implements IEnrollmentController {
  private final IEnrollmentService enrollmentService;

  @Override
  public ResponseEntity<GeneralResponse<EnrollmentResponseDto>> createEnrollment(
      EnrollmentCreateRequestDto enrollmentCreateRequestDto) {
    log.info("=========== Start create enrollment request ===========");
    EnrollmentResponseDto responseDto = enrollmentService.createEnrollment(enrollmentCreateRequestDto);
    return new ResponseEntity<>((new GeneralResponse<>(HttpStatus.CREATED.value(), "Create enrollment successfully", responseDto)), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<EnrollmentResponseDto>> updateEnrollment(
      String enrollmentId, EnrollmentUpdateRequestDto enrollmentUpdateRequestDto) {
    log.info("=========== Start update enrollment request ===========");
    EnrollmentResponseDto responseDto = enrollmentService.updateEnrollment(enrollmentId, enrollmentUpdateRequestDto);
    return new ResponseEntity<>((new GeneralResponse<>(HttpStatus.OK.value(), "Update enrollment successfully", responseDto)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<EnrollmentResponseDto>> getEnrollment(String enrollmentId) {
    log.info("=========== Start get enrollment request ===========");
    EnrollmentResponseDto responseDto = enrollmentService.getEnrollment(enrollmentId);
    return new ResponseEntity<>(
        (new GeneralResponse<>(HttpStatus.OK.value(), "Get enrollment successfully", responseDto)),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<EnrollmentResponseDto>>> getEnrollmentsByFilter(
      EnrollmentFilterRequestDto enrollmentFilterRequestDto) {
    log.info("=========== Start get enrollments by filter request ===========");
    List<EnrollmentResponseDto> responseDto = enrollmentService.filterAllEnrollments(enrollmentFilterRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Create enrollment successfully", responseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteEnrollment(
      String enrollmentId) {
    log.info("=========== Start delete enrollment request ===========");
    enrollmentService.deleteEnrollment(enrollmentId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Delete enrollment successfully", null), HttpStatus.OK);
  }
}
