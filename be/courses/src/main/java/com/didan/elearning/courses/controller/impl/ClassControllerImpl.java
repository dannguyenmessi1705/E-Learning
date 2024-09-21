package com.didan.elearning.courses.controller.impl;

import com.didan.elearning.courses.controller.IClassController;
import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.request.ClassUpdateRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.service.ICourseClassesService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ClassControllerImpl implements IClassController {
  private final ICourseClassesService courseClassesService;
  @Override
  public ResponseEntity<GeneralResponse<ClassResponseDto>> createClass(
      ClassRequestDto classRequestDto) {
    log.info("Creating a new class...");
    ClassResponseDto classResponseDto = courseClassesService.createClassOfCourse(classRequestDto);
    GeneralResponse<ClassResponseDto> response = new GeneralResponse<>(HttpStatus.CREATED.value(), "Class created successfully", classResponseDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getClassesByInstructor(String instructorId) {
    log.info("Getting classes of instructor {}...", instructorId);
    List<ClassResponseDto> classResponse = courseClassesService.getAllClassesByInstructor(instructorId);
    GeneralResponse<List<ClassResponseDto>> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning classes of instructor " + instructorId, classResponse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getClassesByAssistant(String assistantId) {
    log.info("Getting classes of assistant {}...", assistantId);
    List<ClassResponseDto> classResponse = courseClassesService.getAllClassesByAssistant(assistantId);
    GeneralResponse<List<ClassResponseDto>> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning classes of assistant " + assistantId, classResponse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassResponseDto>> updateClass(
      ClassUpdateRequestDto classUpdateRequestDto) {
    log.info("Updating class {}...", classUpdateRequestDto.getClassCode());
    ClassResponseDto classResponseDto = courseClassesService.updateCourseClasses(classUpdateRequestDto);
    GeneralResponse<ClassResponseDto> response = new GeneralResponse<>(HttpStatus.OK.value(), "Class updated successfully", classResponseDto);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteClass(String classCode) {
    log.info("Deleting class {}...", classCode);
    courseClassesService.deleteCourseClasses(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Class deleted successfully", null), HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassResponseDto>> getClassByCode(String classCode) {
    log.info("Getting class {}...", classCode);
    ClassResponseDto classResponseDto = courseClassesService.getClassByCode(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Returning class " + classCode, classResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassResponseDto>>> getClassesOfCourse(
      String courseCode) {
    log.info("Getting classes of course {}...", courseCode);
    List<ClassResponseDto> classResponseDtos = courseClassesService.getAllClassesOfCourse(courseCode);
    GeneralResponse<List<ClassResponseDto>> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning classes of course " + courseCode, classResponseDtos);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
