package com.didan.elearning.enrollments.controller.impl;

import com.didan.elearning.enrollments.controller.IClassRegistrationDetailsController;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsFilterRequest;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.ClassRegistrationDetailsResponseDto;
import com.didan.elearning.enrollments.dto.response.GeneralResponse;
import com.didan.elearning.enrollments.service.IClassRegistrationDetailsService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ClassRegistrationDetailsControllerImpl implements IClassRegistrationDetailsController {

  private final IClassRegistrationDetailsService classRegistrationDetailsService;

  @Override
  public ResponseEntity<GeneralResponse<ClassRegistrationDetailsResponseDto>> createClassRegistrationDetails(
      ClassRegistrationDetailsCreateRequestDto classRegistrationDetailsCreateRequestDto) {
    log.info("=========== Start create class registration details request ===========");
    ClassRegistrationDetailsResponseDto responseDto = classRegistrationDetailsService.createClassRegistrationDetails(
        classRegistrationDetailsCreateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(),
        "Create class registration details successfully", responseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassRegistrationDetailsResponseDto>> updateClassRegistrationDetails(
      String registrationId,
      ClassRegistrationDetailsUpdateRequestDto classRegistrationDetailsUpdateRequestDto) {
    log.info("=========== Start update class registration details request ===========");
    ClassRegistrationDetailsResponseDto responseDto = classRegistrationDetailsService.updateClassRegistrationDetails(
        registrationId, classRegistrationDetailsUpdateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        "Update class registration details successfully", responseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassRegistrationDetailsResponseDto>> getClassRegistrationDetails(
      String registrationId) {
    log.info("=========== Start get class registration details request ===========");
    ClassRegistrationDetailsResponseDto responseDto = classRegistrationDetailsService.getClassRegistrationDetails(
        registrationId);
    return new ResponseEntity<>(
        new GeneralResponse<>(HttpStatus.OK.value(), "Get class registration details successfully",
            responseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassRegistrationDetailsResponseDto>>> filterClassRegistrationDetails(
      ClassRegistrationDetailsFilterRequest classRegistrationDetailsFilterRequest) {
    log.info("=========== Start filter class registration details request ===========");
    List<ClassRegistrationDetailsResponseDto> responseDto = classRegistrationDetailsService.filterClassRegistrationDetails(
        classRegistrationDetailsFilterRequest);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        "Filter class registration details successfully", responseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteClassRegistrationDetails(
      String registrationId) {
    log.info("=========== Start delete class registration details request ===========");
    classRegistrationDetailsService.deleteClassRegistrationDetails(registrationId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        "Delete class registration details successfully", null), HttpStatus.OK);

  }
}
