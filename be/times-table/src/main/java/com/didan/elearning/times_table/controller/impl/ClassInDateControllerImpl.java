package com.didan.elearning.times_table.controller.impl;

import com.didan.elearning.times_table.controller.IClassInDateController;
import com.didan.elearning.times_table.dto.request.ClassInDateRequestDto;
import com.didan.elearning.times_table.dto.response.ClassInDateResponseDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import com.didan.elearning.times_table.service.IClassInDateService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ClassInDateControllerImpl implements IClassInDateController {
  private final IClassInDateService classInDateService;

  @Override
  public ResponseEntity<GeneralResponse<ClassInDateResponseDto>> createClassInDate(
      ClassInDateRequestDto classInDateRequestDto) {
    log.info("Create class in date: {}", classInDateRequestDto);
    ClassInDateResponseDto classInDateResponseDto = classInDateService.createClassInDate(classInDateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Class in date created", classInDateResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassInDateResponseDto>> updateClassInDate(
      String classInDateId, ClassInDateRequestDto classInDateRequestDto) {
    log.info("Update class in date: {}", classInDateRequestDto);
    ClassInDateResponseDto classInDateResponseDto = classInDateService.updateClassInDate(classInDateId, classInDateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Class in date updated", classInDateResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassInDateResponseDto>>> getAllClassInDateByClassCodeAndDate(
      String classCode, String date) {
    log.info("Get all class in date by class code: {} and date: {}", classCode, date);
    List<ClassInDateResponseDto> classInDateResponseDtos = classInDateService.getAllClassInDateByClassCodeAndDateScheduleId(classCode, date);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get all class in date by class code and date", classInDateResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassInDateResponseDto>> getClassInDateById(
      String classInDateId) {
    log.info("Get class in date by id: {}", classInDateId);
    ClassInDateResponseDto classInDateResponseDto = classInDateService.getClassInDateById(classInDateId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get class in date by id", classInDateResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteClassInDate(String classInDateId) {
    log.info("Delete class in date by id: {}", classInDateId);
    classInDateService.deleteClassInDate(classInDateId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Class in date deleted", null), HttpStatus.NO_CONTENT);
  }
}
