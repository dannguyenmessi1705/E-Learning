package com.didan.elearning.enrollments.service;

import com.didan.elearning.enrollments.dto.request.EnrollmentCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentFilterRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.EnrollmentResponseDto;
import java.util.List;

public interface IEnrollmentService {
  EnrollmentResponseDto createEnrollment(EnrollmentCreateRequestDto enrollmentCreateRequestDto);
  EnrollmentResponseDto getEnrollment(String enrollmentId);
  EnrollmentResponseDto updateEnrollment(String enrollmentId, EnrollmentUpdateRequestDto enrollmentUpdateRequestDto);
  void deleteEnrollment(String enrollmentId);
  List<EnrollmentResponseDto> filterAllEnrollments(EnrollmentFilterRequestDto enrollmentFilterRequestDto);
}
