package com.didan.elearning.times_table.service.impl;

import com.didan.elearning.times_table.constant.MessageConstant;
import com.didan.elearning.times_table.dto.request.TimeClassRequestDto;
import com.didan.elearning.times_table.dto.response.TimeClassResponseDto;
import com.didan.elearning.times_table.entity.TimeClasses;
import com.didan.elearning.times_table.exception.FieldErrorException;
import com.didan.elearning.times_table.exception.ResourceAlreadyExistException;
import com.didan.elearning.times_table.exception.ResourceNotFoundException;
import com.didan.elearning.times_table.repository.TimeClassRepository;
import com.didan.elearning.times_table.service.ITimeClassService;
import com.didan.elearning.times_table.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class TimeClassServiceImpl implements ITimeClassService {

  private final TimeClassRepository timeClassRepository;

  @Override
  public TimeClassResponseDto createTimeClass(TimeClassRequestDto timeClassRequestDto) {
    validateCreateRequest(timeClassRequestDto);
    /**
     * OpenFeign call to course service to get course details
     * if course not found throw ResourceNotFoundException
     * OpenFeign call to course service to get class details
     * if class not found throw ResourceNotFoundException
     * OpenFeign call to user service to get instructor details
     * if instructor not found throw ResourceNotFoundException
     * if exist, assign courseCode, classCode, instructorId to TimeClassResponseDto
     */
    validateExistTimeClass(timeClassRequestDto);
    /**
     * OpenFeign to get course name + class code + instructor name
     */
    String className = "Data Structure and Algorithm - " + timeClassRequestDto.getClassCode() + " - " + "Nguyen Van A";
    TimeClasses newTimeClass = TimeClasses.builder()
        .courseCode(timeClassRequestDto.getCourseCode())
        .classCode(timeClassRequestDto.getClassCode())
        .instructorId(timeClassRequestDto.getInstructorId())
        .className(className)
        .build();
    timeClassRepository.save(newTimeClass);
    log.info("Time class created with course code: {}, class code: {}, instructor ID: {}",
        timeClassRequestDto.getCourseCode(), timeClassRequestDto.getClassCode(),
        timeClassRequestDto.getInstructorId());
    return MapperUtils.map(newTimeClass, TimeClassResponseDto.class);
  }

  @Override
  public TimeClassResponseDto updateTimeClass(String timeClassId,
      TimeClassRequestDto timeClassRequestDto) {
    TimeClasses timeClasses = timeClassRepository.findById(timeClassId)
        .orElseThrow(() -> {
          log.error("Time class not found with ID: {}", timeClassId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.TIME_CLASS_NOT_FOUND, timeClassId));
        });
    if (StringUtils.hasText(timeClassRequestDto.getCourseCode())) {
      /**
       * OpenFeign call to course service to get course details
       * if course not found throw ResourceNotFoundException
       * if exist, assign courseCode to TimeClassResponseDto
       */
      timeClasses.setCourseCode(timeClassRequestDto.getCourseCode());
    }
    if (StringUtils.hasText(timeClassRequestDto.getClassCode())) {
      /**
       * OpenFeign call to course service to get class details
       * if class not found throw ResourceNotFoundException
       * if exist, assign classCode to TimeClassResponseDto
       */
      timeClasses.setClassCode(timeClassRequestDto.getClassCode());
    }
    if (StringUtils.hasText(timeClassRequestDto.getInstructorId())) {
      /**
       * OpenFeign call to user service to get instructor details
       * if instructor not found throw ResourceNotFoundException
       * if exist, assign instructorId to TimeClassResponseDto
       */
      timeClasses.setInstructorId(timeClassRequestDto.getInstructorId());
    }
    /**
     * OpenFeign to get course name + class code + instructor name
     */
    String className = "Data Structure and Algorithm - " + timeClassRequestDto.getClassCode() + " - " + "Nguyen Van A";
    timeClasses.setClassName(className);
    validateExistTimeClass(MapperUtils.map(timeClasses, TimeClassRequestDto.class));
    timeClassRepository.save(timeClasses);
    log.info("Time class updated with ID: {}", timeClassId);
    return MapperUtils.map(timeClasses, TimeClassResponseDto.class);
  }

  @Override
  public TimeClassResponseDto getTimeClass(String timeClassId) {
    TimeClasses timeClasses = timeClassRepository.findById(timeClassId)
        .orElseThrow(() -> {
          log.error("Time class not found with ID: {}", timeClassId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.TIME_CLASS_NOT_FOUND, timeClassId));
        });
    return MapperUtils.map(timeClasses, TimeClassResponseDto.class);
  }

  @Override
  public List<TimeClassResponseDto> getTimeClassesByClassCode(String classCode) {
    /**
     * OpenFeign call to course service to get course details
     * if course not found throw ResourceNotFoundException
     * if exist, assign courseCode to TimeClassResponseDto
     */
    List<TimeClasses> timeClasses = timeClassRepository.findTimeClassesByClassCodeIgnoreCase(classCode);
    if (timeClasses.isEmpty()) {
      log.error("Time classes not found with class code: {}", classCode);
      return List.of();
    }
    return MapperUtils.mapList(timeClasses, TimeClassResponseDto.class);
  }

  @Override
  public List<TimeClassResponseDto> getTimeClassesByInstructorId(String instructorId) {
    /**
     * OpenFeign call to user service to get instructor details
     * if instructor not found throw ResourceNotFoundException
     * if exist, assign instructorId to TimeClassResponseDto
     */
    List<TimeClasses> timeClasses = timeClassRepository.findTimeClassesByInstructorIdIgnoreCase(instructorId);
    if (timeClasses.isEmpty()) {
      log.error("Time classes not found with instructor ID: {}", instructorId);
      return List.of();
    }
    return MapperUtils.mapList(timeClasses, TimeClassResponseDto.class);
  }

  @Override
  public List<TimeClassResponseDto> getTimeClassesByCourseCode(String courseCode) {
    /**
     * OpenFeign call to course service to get course details
     * if course not found throw ResourceNotFoundException
     * if exist, assign courseCode to TimeClassResponseDto
     */
    List<TimeClasses> timeClasses = timeClassRepository.findTimeClassesByCourseCodeIgnoreCase(courseCode);
    if (timeClasses.isEmpty()) {
      log.error("Time classes not found with course code: {}", courseCode);
      return List.of();
    }
    return MapperUtils.mapList(timeClasses, TimeClassResponseDto.class);
  }

  @Override
  @Transactional
  @Modifying
  public void deleteTimeClass(String timeClassId) {
    TimeClasses timeClasses = timeClassRepository.findById(timeClassId)
        .orElseThrow(() -> {
          log.error("Time class not found with ID: {}", timeClassId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.TIME_CLASS_NOT_FOUND, timeClassId));
        });
    timeClassRepository.delete(timeClasses);
    log.info("Time class deleted with ID: {}", timeClassId);
  }

  public void validateCreateRequest(TimeClassRequestDto timeClassRequestDto) {
    if (!StringUtils.hasText(timeClassRequestDto.getCourseCode())) {
      log.error("Course code is required");
      throw new FieldErrorException("Course code is required");
    }
    if (!StringUtils.hasText(timeClassRequestDto.getClassCode())) {
      log.error("Class code is required");
      throw new FieldErrorException("Class code is required");
    }
    if (!StringUtils.hasText(timeClassRequestDto.getInstructorId())) {
      log.error("Instructor ID is required");
      throw new FieldErrorException("Instructor ID is required");
    }
  }

  public void validateExistTimeClass(TimeClassRequestDto timeClassRequestDto) {
    timeClassRepository.findTimeClassesByCourseCodeIgnoreCaseAndClassCodeIgnoreCaseAndInstructorIdIgnoreCase(
        timeClassRequestDto.getCourseCode(),
        timeClassRequestDto.getClassCode(),
        timeClassRequestDto.getInstructorId()
    ).ifPresent(timeClasses -> {
      log.error("Time class already exists with course code: {}, class code: {}, instructor ID: {}",
          timeClassRequestDto.getCourseCode(),
          timeClassRequestDto.getClassCode(),
          timeClassRequestDto.getInstructorId()
      );
      throw new ResourceAlreadyExistException(
          String.format(MessageConstant.TIME_CLASS_ALREADY_EXIST_WITH_COURSE_CLASS_INSTRUCTOR,
              timeClassRequestDto.getCourseCode(), timeClassRequestDto.getClassCode(),
              timeClassRequestDto.getInstructorId()));
    });
  }
}
