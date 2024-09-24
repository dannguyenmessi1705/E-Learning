package com.didan.elearning.enrollments.service.impl;

import com.didan.elearning.enrollments.constant.ClassStatusConstant;
import com.didan.elearning.enrollments.constant.MessageConstant;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsFilterRequest;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.ClassRegistrationDetailsResponseDto;
import com.didan.elearning.enrollments.entity.ClassRegistrationDetails;
import com.didan.elearning.enrollments.exception.FieldErrorException;
import com.didan.elearning.enrollments.exception.ResourceAlreadyExistException;
import com.didan.elearning.enrollments.exception.ResourceNotFoundException;
import com.didan.elearning.enrollments.repository.ClassRegistrationDetailsRepository;
import com.didan.elearning.enrollments.service.IClassRegistrationDetailsService;
import com.didan.elearning.enrollments.service.IClientCourseService;
import com.didan.elearning.enrollments.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassRegistrationDetailsServiceImpl implements IClassRegistrationDetailsService {

  private final ClassRegistrationDetailsRepository classRegistrationDetailsRepository;
  private final IClientCourseService clientCourseService;

  @Override
  public ClassRegistrationDetailsResponseDto createClassRegistrationDetails(
      ClassRegistrationDetailsCreateRequestDto requestDto) {
    if (!this.clientCourseService.checkClassExisted(requestDto.getClassCode(), requestDto.getCourseCode(),
        requestDto.getSemesterCode())) {
      log.error("Class registration details not created because class not found");
      throw new ResourceNotFoundException("Class registration details not created because class not found");
    } else {
      this.classRegistrationDetailsRepository.findClassRegistrationDetailsByClassCodeAndCourseCodeAndSemesterCode(
          StringUtils.capitalize(requestDto.getClassCode()), StringUtils.capitalize(requestDto.getCourseCode()),
          StringUtils.capitalize(requestDto.getSemesterCode())).ifPresent((classRegistrationDetailsx) -> {
            log.error("Class registration details already existed");
            throw new ResourceAlreadyExistException("Class registration details already existed");
          });
      ClassRegistrationDetails classRegistrationDetails = MapperUtils.map(
          requestDto, ClassRegistrationDetails.class);
      this.classRegistrationDetailsRepository.save(classRegistrationDetails);
      log.info(String.format(MessageConstant.CREATE_CLASS_REGISTRATION_DETAILS_SUCCESS,
          classRegistrationDetails.getClassDetailsId(), classRegistrationDetails.getClassCode(),
          classRegistrationDetails.getSemesterCode(), classRegistrationDetails.getCourseCode()));
      return MapperUtils.map(classRegistrationDetails, ClassRegistrationDetailsResponseDto.class);
    }
  }

  @Override
  @Transactional
  public ClassRegistrationDetailsResponseDto updateClassRegistrationDetails(
      String classRegistrationId,
      ClassRegistrationDetailsUpdateRequestDto classRegistrationDetailsUpdateRequestDto) {
    ClassRegistrationDetails classRegistrationDetails = classRegistrationDetailsRepository.findById(
            classRegistrationId)
        .orElseThrow(() -> {
          log.info("Class registration details not found with id {}", classRegistrationId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.CLASS_REGISTRATION_DETAILS_NOT_FOUND,
                  classRegistrationId));
        });
    if (StringUtils.hasText(classRegistrationDetailsUpdateRequestDto.getClassCode())) {
      /**
       * OpenFeign call to course service to check if class exists
       * classRegistrationDetailsUpdateRequestDto.getClassCode()
       * if not exists, throw exception
       */
    }
    if (StringUtils.hasText(classRegistrationDetailsUpdateRequestDto.getSemesterCode())) {
      /**
       * OpenFeign call to course service to check if semester exists
       * classRegistrationDetailsUpdateRequestDto.getSemesterCode()
       * if not exists, throw exception
       */
    }
    if (StringUtils.hasText(classRegistrationDetailsUpdateRequestDto.getCourseCode())) {
      /**
       * OpenFeign call to course service to check if course exists
       * classRegistrationDetailsUpdateRequestDto.getCourseCode()
       * if not exists, throw exception
       */
    }
    if (StringUtils.hasText(
        String.valueOf(classRegistrationDetailsUpdateRequestDto.getMaxCapacity()))) {
      classRegistrationDetails.setMaxCapacity(
          classRegistrationDetailsUpdateRequestDto.getMaxCapacity());
    }
    if (StringUtils.hasText(
        String.valueOf(classRegistrationDetailsUpdateRequestDto.getCurrentEnrollmentQuantity()))) {
      classRegistrationDetails.setCurrentEnrollmentQuantity(
          classRegistrationDetailsUpdateRequestDto.getCurrentEnrollmentQuantity());
    }
    if (StringUtils.hasText(classRegistrationDetailsUpdateRequestDto.getStatusClass())) {
      String status = classRegistrationDetailsUpdateRequestDto.getStatusClass().toUpperCase();
      if (status.equals(ClassStatusConstant.ACTIVE)) {
        classRegistrationDetails.setStatusClass(ClassStatusConstant.ACTIVE);
      } else if (status.equals(ClassStatusConstant.INACTIVE)) {
        classRegistrationDetails.setStatusClass(ClassStatusConstant.INACTIVE);
      } else {
        throw new FieldErrorException("Invalid status class");
      }
    }
    classRegistrationDetailsRepository.save(classRegistrationDetails);
    log.info(String.format(MessageConstant.UPDATE_CLASS_REGISTRATION_DETAILS_SUCCESS,
        classRegistrationDetails.getClassDetailsId(), classRegistrationDetails.getClassCode(),
        classRegistrationDetails.getSemesterCode(), classRegistrationDetails.getCourseCode()));
    return MapperUtils.map(classRegistrationDetails, ClassRegistrationDetailsResponseDto.class);
  }

  @Override
  public ClassRegistrationDetailsResponseDto getClassRegistrationDetails(String classRegistrationId) {
    ClassRegistrationDetails classRegistrationDetails = classRegistrationDetailsRepository.findById(
            classRegistrationId)
        .orElseThrow(() -> {
          log.info("Class registration details not found with id {}", classRegistrationId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.CLASS_REGISTRATION_DETAILS_NOT_FOUND,
                  classRegistrationId));
        });
    log.info("Get class registration details with id {}", classRegistrationId);
    return MapperUtils.map(classRegistrationDetails, ClassRegistrationDetailsResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteClassRegistrationDetails(String classRegistrationId) {
    ClassRegistrationDetails classRegistrationDetails = classRegistrationDetailsRepository.findById(
            classRegistrationId)
        .orElseThrow(() -> {
          log.info("Class registration details not found with id {}", classRegistrationId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.CLASS_REGISTRATION_DETAILS_NOT_FOUND,
                  classRegistrationId));
        });
    classRegistrationDetailsRepository.delete(classRegistrationDetails);
    log.info("Delete class registration details with id {}", classRegistrationId);
  }

  @Override
  public List<ClassRegistrationDetailsResponseDto> filterClassRegistrationDetails(
      ClassRegistrationDetailsFilterRequest classRegistrationDetailsFilterRequest) {
    List<ClassRegistrationDetails> classRegistrationDetailsList = new ArrayList<>();
    int caseType = 0;
    if (StringUtils.hasText(classRegistrationDetailsFilterRequest.getSemesterCode())) {
      /**
       * OpenFeign call to course service to check if semester exists
       * classRegistrationDetailsFilterRequest.getSemesterCode()
       * if not exists, throw exception
       */
      caseType += 1;
    }
    if (StringUtils.hasText(classRegistrationDetailsFilterRequest.getCourseCode())) {
      /**
       * OpenFeign call to course service to check if course exists
       * classRegistrationDetailsFilterRequest.getCourseCode()
       * if not exists, throw exception
       */
      caseType += 3;
    }
    if (StringUtils.hasText(classRegistrationDetailsFilterRequest.getClassCode())) {
      /**
       * OpenFeign call to course service to check if class exists
       * classRegistrationDetailsFilterRequest.getClassCode()
       * if not exists, throw exception
       */
      caseType += 5;
    }
    switch (caseType) {
      case 1 -> classRegistrationDetailsList = classRegistrationDetailsRepository
            .findClassRegistrationDetailsBySemesterCodeIgnoreCase(
                classRegistrationDetailsFilterRequest.getSemesterCode());
      case 3 -> classRegistrationDetailsList = classRegistrationDetailsRepository
          .findClassRegistrationDetailsByCourseCodeIgnoreCase(
              classRegistrationDetailsFilterRequest.getCourseCode());
      case 5 -> classRegistrationDetailsList = classRegistrationDetailsRepository
          .findClassRegistrationDetailsByClassCodeIgnoreCase(
              classRegistrationDetailsFilterRequest.getClassCode());
      case 4 -> classRegistrationDetailsList = classRegistrationDetailsRepository
          .findClassRegistrationDetailsBySemesterCodeIgnoreCaseAndCourseCodeIgnoreCase(
              classRegistrationDetailsFilterRequest.getSemesterCode(),
              classRegistrationDetailsFilterRequest.getCourseCode());
      case 6 -> classRegistrationDetailsList = classRegistrationDetailsRepository
          .findClassRegistrationDetailsBySemesterCodeIgnoreCaseAndClassCodeIgnoreCase(
              classRegistrationDetailsFilterRequest.getSemesterCode(),
              classRegistrationDetailsFilterRequest.getClassCode());
      case 8 -> classRegistrationDetailsList = classRegistrationDetailsRepository
          .findClassRegistrationDetailsByCourseCodeIgnoreCaseAndClassCodeIgnoreCase(
              classRegistrationDetailsFilterRequest.getCourseCode(),
              classRegistrationDetailsFilterRequest.getClassCode());
      case 9 -> classRegistrationDetailsList = classRegistrationDetailsRepository
          .findClassRegistrationDetailsBySemesterCodeIgnoreCaseAndCourseCodeIgnoreCaseAndClassCodeIgnoreCase(
              classRegistrationDetailsFilterRequest.getSemesterCode(),
              classRegistrationDetailsFilterRequest.getCourseCode(),
              classRegistrationDetailsFilterRequest.getClassCode());
      default -> {
        log.info("Filter class registration details with empty request");
        return List.of();
      }
    }
    if (classRegistrationDetailsList.isEmpty()) {
      log.info("Class registration details not found with filter request");
      return List.of();
    }
    log.info("Filter class registration details with filter request");
    return MapperUtils.mapList(classRegistrationDetailsList, ClassRegistrationDetailsResponseDto.class);
  }
}
