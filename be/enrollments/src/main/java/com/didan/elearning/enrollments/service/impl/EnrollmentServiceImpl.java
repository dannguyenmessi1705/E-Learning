package com.didan.elearning.enrollments.service.impl;

import com.didan.elearning.enrollments.constant.EnrollmentStatusConstant;
import com.didan.elearning.enrollments.constant.MessageConstant;
import com.didan.elearning.enrollments.dto.request.EnrollmentCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentFilterRequestDto;
import com.didan.elearning.enrollments.dto.request.EnrollmentUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.EnrollmentResponseDto;
import com.didan.elearning.enrollments.entity.ClassRegistrationDetails;
import com.didan.elearning.enrollments.entity.Enrollment;
import com.didan.elearning.enrollments.exception.ResourceNotFoundException;
import com.didan.elearning.enrollments.repository.ClassRegistrationDetailsRepository;
import com.didan.elearning.enrollments.repository.EnrollmentRepository;
import com.didan.elearning.enrollments.service.IEnrollmentService;
import com.didan.elearning.enrollments.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements IEnrollmentService {

  private final ClassRegistrationDetailsRepository classRegistrationDetailsRepository;
  private final EnrollmentRepository enrollmentRepository;

  @Override
  public EnrollmentResponseDto createEnrollment(
      EnrollmentCreateRequestDto enrollmentCreateRequestDto) {
    ClassRegistrationDetails classRegistrationDetails = classRegistrationDetailsRepository.findById(
            enrollmentCreateRequestDto.getClassDetailsId())
        .orElseThrow(() -> {
          log.error("Class registration details not found with id: {}",
              enrollmentCreateRequestDto.getClassDetailsId());
          return new ResourceNotFoundException(
              String.format(MessageConstant.CLASS_REGISTRATION_DETAILS_NOT_FOUND,
                  enrollmentCreateRequestDto.getClassDetailsId()));
        });
    /**
     * OpenFeign call to user service to check if student exists
     * enrollmentCreateRequestDto.getStudentCode()
     * if student does not exist throw ResourceNotFoundException
     */
    Enrollment newEnrollment = MapperUtils.map(enrollmentCreateRequestDto, Enrollment.class);
    newEnrollment.setClassRegistrationDetails(classRegistrationDetails);
    enrollmentRepository.save(newEnrollment);
    log.info("Enrollment created successfully with id: {}", newEnrollment.getEnrollmentId());
    EnrollmentResponseDto responseDto = MapperUtils.map(newEnrollment, EnrollmentResponseDto.class);
    responseDto.setClassDetailsId(newEnrollment.getClassRegistrationDetails().getClassDetailsId());
    return responseDto;
  }

  @Override
  public EnrollmentResponseDto getEnrollment(String enrollmentId) {
    Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
        .orElseThrow(() -> {
          log.error("Enrollment not found with id: {}", enrollmentId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.ENROLLMENT_NOT_FOUND, enrollmentId));
        });
    EnrollmentResponseDto response = MapperUtils.map(enrollment, EnrollmentResponseDto.class);
    response.setClassDetailsId(enrollment.getClassRegistrationDetails().getClassDetailsId());
    return response;
  }

  @Override
  @Transactional
  public EnrollmentResponseDto updateEnrollment(String enrollmentId,
      EnrollmentUpdateRequestDto enrollmentUpdateRequestDto) {
    Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
        .orElseThrow(() -> {
          log.error("Enrollment not found with id: {}", enrollmentId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.ENROLLMENT_NOT_FOUND, enrollmentId));
        });
    if (enrollmentUpdateRequestDto.getStatus().equalsIgnoreCase(EnrollmentStatusConstant.ACTIVE)) {
      enrollment.setStatus(EnrollmentStatusConstant.ACTIVE);
    } else if (enrollmentUpdateRequestDto.getStatus()
        .equalsIgnoreCase(EnrollmentStatusConstant.INACTIVE)) {
      enrollment.setStatus(EnrollmentStatusConstant.INACTIVE);
    } else if (enrollmentUpdateRequestDto.getStatus()
        .equalsIgnoreCase(EnrollmentStatusConstant.PENDING)) {
      enrollment.setStatus(EnrollmentStatusConstant.PENDING);
    } else if (enrollmentUpdateRequestDto.getStatus()
        .equalsIgnoreCase(EnrollmentStatusConstant.COMPLETED)) {
      enrollment.setStatus(EnrollmentStatusConstant.COMPLETED);
    } else if (enrollmentUpdateRequestDto.getStatus()
        .equalsIgnoreCase(EnrollmentStatusConstant.DROPPED)) {
      enrollment.setStatus(EnrollmentStatusConstant.DROPPED);
    } else {
      log.error("Invalid status: {}", enrollmentUpdateRequestDto.getStatus());
      throw new ResourceNotFoundException("Invalid status");
    }
    enrollmentRepository.save(enrollment);
    log.info("Enrollment updated successfully with id: {}", enrollment.getEnrollmentId());
    EnrollmentResponseDto response = MapperUtils.map(enrollment, EnrollmentResponseDto.class);
    response.setClassDetailsId(enrollment.getClassRegistrationDetails().getClassDetailsId());
    return response;
  }

  @Override
  @Transactional
  public void deleteEnrollment(String enrollmentId) {
    Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
        .orElseThrow(() -> {
          log.error("Enrollment not found with id: {}", enrollmentId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.ENROLLMENT_NOT_FOUND, enrollmentId));
        });
    enrollmentRepository.delete(enrollment);
    log.info("Enrollment deleted successfully with id: {}", enrollmentId);
  }

  @Override
  public List<EnrollmentResponseDto> filterAllEnrollments(
      EnrollmentFilterRequestDto enrollmentFilterRequestDto) {
    int typeFilter = 0;
    if (StringUtils.hasText(enrollmentFilterRequestDto.getStudentCode())) {
      /**
       * OpenFeign call to user service to check if student exists
       * enrollmentFilterRequestDto.getStudentCode()
       * if student does not exist throw ResourceNotFoundException
       */
      typeFilter += 1;
    }
    if (StringUtils.hasText(enrollmentFilterRequestDto.getClassDetailsId())) {
      ClassRegistrationDetails classRegistrationDetails = classRegistrationDetailsRepository.findById(
              enrollmentFilterRequestDto.getClassDetailsId())
          .orElseThrow(() -> {
            log.error("Class registration details not found with id: {}",
                enrollmentFilterRequestDto.getClassDetailsId());
            return new ResourceNotFoundException(
                String.format(MessageConstant.CLASS_REGISTRATION_DETAILS_NOT_FOUND,
                    enrollmentFilterRequestDto.getClassDetailsId()));
          });
      typeFilter += 2;
    }
    List<Enrollment> enrollments = new ArrayList<>();
    List<EnrollmentResponseDto> responses = new ArrayList<>();
    switch (typeFilter) {
      case 1 -> {
        enrollments = enrollmentRepository.findEnrollmentByStudentCodeIgnoreCase(
            enrollmentFilterRequestDto.getStudentCode());
        EnrollmentResponseDto response = new EnrollmentResponseDto();
        for (Enrollment enrollment : enrollments) {
          response = MapperUtils.map(enrollment, response);
          response.setClassDetailsId(enrollment.getClassRegistrationDetails().getClassDetailsId());
          responses.add(response);
        }
      }
      case 2 -> {
        enrollments = enrollmentRepository.findEnrollmentByClassRegistrationDetails_ClassDetailsIdIgnoreCase(
            enrollmentFilterRequestDto.getClassDetailsId());
        EnrollmentResponseDto response = new EnrollmentResponseDto();
        for (Enrollment enrollment : enrollments) {
          response = MapperUtils.map(enrollment, response);
          response.setClassDetailsId(enrollment.getClassRegistrationDetails().getClassDetailsId());
          responses.add(response);
        }
      }
      case 3 -> {
        enrollments = enrollmentRepository.findEnrollmentByStudentCodeIgnoreCaseAndClassRegistrationDetails_ClassDetailsIdIgnoreCase(
            enrollmentFilterRequestDto.getStudentCode(),
            enrollmentFilterRequestDto.getClassDetailsId());
        EnrollmentResponseDto response = new EnrollmentResponseDto();
        for (Enrollment enrollment : enrollments) {
          response = MapperUtils.map(enrollment, response);
          response.setClassDetailsId(enrollment.getClassRegistrationDetails().getClassDetailsId());
          responses.add(response);
        }
      }
      default -> {
        log.info("Filter class registration details with empty request");
        enrollments = List.of();
      }
    }
    ;
    log.info("Filter enrollments successfully");
    return responses;
  }
}
