package com.didan.elearning.courses.controller.impl;

import com.didan.elearning.courses.controller.IClassController;
import com.didan.elearning.courses.dto.request.ClassRequestDto;
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
