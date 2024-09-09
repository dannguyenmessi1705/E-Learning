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
  public ResponseEntity<MappingJacksonValue> createClass(
      ClassRequestDto classRequestDto) {
    log.info("Creating a new class...");
    ClassResponseDto classResponseDto = courseClassesService.createClassOfCourse(classRequestDto);
    GeneralResponse<ClassResponseDto> response = new GeneralResponse<>(HttpStatus.CREATED.value(), "Class created successfully", classResponseDto);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<MappingJacksonValue> getClassesByInstructor(String instructorId) {
    log.info("Getting classes of instructor {}...", instructorId);
    List<ClassResponseDto> classResponse = courseClassesService.getAllClassesByInstructor(instructorId);
    GeneralResponse<List<ClassResponseDto>> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning classes of instructor " + instructorId, classResponse);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MappingJacksonValue> getClassesByAssistant(String assistantId) {
    log.info("Getting classes of assistant {}...", assistantId);
    List<ClassResponseDto> classResponse = courseClassesService.getAllClassesByAssistant(assistantId);
    GeneralResponse<List<ClassResponseDto>> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning classes of assistant " + assistantId, classResponse);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MappingJacksonValue> updateClass(
      ClassUpdateRequestDto classUpdateRequestDto) {
    log.info("Updating class {}...", classUpdateRequestDto.getClassCode());
    ClassResponseDto classResponseDto = courseClassesService.updateCourseClasses(classUpdateRequestDto);
    GeneralResponse<ClassResponseDto> response = new GeneralResponse<>(HttpStatus.OK.value(), "Class updated successfully", classResponseDto);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MappingJacksonValue> deleteClass(String classCode) {
    log.info("Deleting class {}...", classCode);
    courseClassesService.deleteCourseClasses(classCode);
    GeneralResponse<String> response = new GeneralResponse<>(HttpStatus.OK.value(), "Class deleted successfully", classCode);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MappingJacksonValue> getClassByCode(String classCode) {
    log.info("Getting class {}...", classCode);
    ClassResponseDto classResponseDto = courseClassesService.getClassByCode(classCode);
    GeneralResponse<ClassResponseDto> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning class " + classCode, classResponseDto);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MappingJacksonValue> getClassesOfCourse(
      String courseCode) {
    log.info("Getting classes of course {}...", courseCode);
    List<ClassResponseDto> classResponseDtos = courseClassesService.getAllClassesOfCourse(courseCode);
    GeneralResponse<List<ClassResponseDto>> response = new GeneralResponse<>(HttpStatus.OK.value(), "Returning classes of course " + courseCode, classResponseDtos);
    MappingJacksonValue mapper = new MappingJacksonValue(response);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("courseCode");
    FilterProvider filters = new SimpleFilterProvider().addFilter("ClassResponseDto", filter);
    mapper.setFilters(filters);
    return new ResponseEntity<>(mapper, HttpStatus.OK);
  }
}
