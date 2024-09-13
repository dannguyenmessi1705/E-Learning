package com.didan.elearning.times_table.service.impl;

import com.didan.elearning.times_table.constant.MessageConstant;
import com.didan.elearning.times_table.dto.request.ClassInDateRequestDto;
import com.didan.elearning.times_table.dto.response.ClassInDateResponseDto;
import com.didan.elearning.times_table.dto.response.DateSchedulesResponseDto;
import com.didan.elearning.times_table.dto.response.TimeClassResponseDto;
import com.didan.elearning.times_table.entity.ClassInDate;
import com.didan.elearning.times_table.entity.DateSchedules;
import com.didan.elearning.times_table.entity.TimeClasses;
import com.didan.elearning.times_table.exception.FieldErrorException;
import com.didan.elearning.times_table.exception.ResourceNotFoundException;
import com.didan.elearning.times_table.repository.ClassInDateRepository;
import com.didan.elearning.times_table.repository.DateSchedulesRepository;
import com.didan.elearning.times_table.repository.TimeClassRepository;
import com.didan.elearning.times_table.service.IClassInDateService;
import com.didan.elearning.times_table.utils.MapperUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class ClassInDateServiceImpl implements IClassInDateService {

  private final ClassInDateRepository classInDateRepository;
  private final TimeClassRepository timeClassRepository;
  private final DateSchedulesRepository dateSchedulesRepository;

  @Override
  public ClassInDateResponseDto createClassInDate(ClassInDateRequestDto classInDateRequestDto) {
    validateRequest(classInDateRequestDto);
    /**
     * OpenFeign call to course service to check classCode and courseCode is exist
     * OpenFeign call to user service to check instructorId is exist
     * if not exist throw ResourceNotFoundException
     * else next step
     */
    TimeClasses timeClasses = timeClassRepository.findTimeClassesByCourseCodeIgnoreCaseAndClassCodeIgnoreCaseAndInstructorIdIgnoreCase(
        classInDateRequestDto.getCourseCode(),
        classInDateRequestDto.getClassCode(),
        classInDateRequestDto.getInstructorId()
    ).orElseThrow(() -> {
      log.error("Time class not found");
      return new ResourceNotFoundException(
          String.format(MessageConstant.TIME_CLASS_NOT_FOUND_WITH_COURSE_CLASS_INSTRUCTOR,
              classInDateRequestDto.getCourseCode(), classInDateRequestDto.getClassCode(),
              classInDateRequestDto.getInstructorId()));
    });

    DateSchedules dateSchedules = dateSchedulesRepository.findDateSchedulesByDate(
        LocalDate.parse(classInDateRequestDto.getDate())).orElseThrow(() -> {
      log.error("Date schedule not found");
      return new ResourceNotFoundException(
          String.format(MessageConstant.DATE_SCHEDULE_NOT_FOUND, classInDateRequestDto.getDate()));
    });

    List<ClassInDate> classInDates = classInDateRepository.findClassInDateByTimeClasses(timeClasses);
    ClassInDate newClassInDate = ClassInDate.builder()
        .classPeriod(classInDates.size() + 1)
        .startTime(LocalTime.parse(classInDateRequestDto.getStartTime()))
        .endTime(LocalTime.parse(classInDateRequestDto.getEndTime()))
        .timeClasses(timeClasses)
        .dateSchedules(dateSchedules)
        .build();
    classInDateRepository.save(newClassInDate);
    log.info("Class in date created with class code: {}, course code: {}, instructor ID: {}",
        classInDateRequestDto.getClassCode(), classInDateRequestDto.getCourseCode(),
        classInDateRequestDto.getInstructorId());
    ClassInDateResponseDto response = MapperUtils.map(newClassInDate, ClassInDateResponseDto.class);
    response.setDate(MapperUtils.map(dateSchedules, DateSchedulesResponseDto.class));
    response.setTime(MapperUtils.map(timeClasses, TimeClassResponseDto.class));
    return response;
  }

  public void validateRequest(ClassInDateRequestDto classInDateRequestDto) {
    if (!StringUtils.hasText(classInDateRequestDto.getClassCode())) {
      log.error("The class code is required");
      throw new FieldErrorException("The class code is required");
    }
    if (!StringUtils.hasText(classInDateRequestDto.getCourseCode())) {
      log.error("The course code is required");
      throw new FieldErrorException("The course code is required");
    }
    if (!StringUtils.hasText(classInDateRequestDto.getInstructorId())) {
      log.error("The instructor ID is required");
      throw new FieldErrorException("The instructor ID is required");
    }
    if (!StringUtils.hasText(classInDateRequestDto.getStartTime())) {
      log.error("The start time is required");
      throw new FieldErrorException("The start time is required");
    }
    if (!StringUtils.hasText(classInDateRequestDto.getEndTime())) {
      log.error("The end time is required");
      throw new FieldErrorException("The end time is required");
    }
    if (!StringUtils.hasText(classInDateRequestDto.getDate())) {
      log.error("The date is required");
      throw new FieldErrorException("The date is required");
    }
  }
}
