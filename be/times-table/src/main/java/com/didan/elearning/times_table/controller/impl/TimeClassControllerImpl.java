package com.didan.elearning.times_table.controller.impl;

import com.didan.elearning.times_table.controller.ITimeClassController;
import com.didan.elearning.times_table.dto.request.TimeClassRequestDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import com.didan.elearning.times_table.dto.response.TimeClassResponseDto;
import com.didan.elearning.times_table.service.ITimeClassService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class TimeClassControllerImpl implements ITimeClassController {
  private final ITimeClassService timeClassService;

  @Override
  public ResponseEntity<GeneralResponse<TimeClassResponseDto>> createTimeClass(
      TimeClassRequestDto timeClassRequestDto) {
    log.info("Creating a new time class");
    TimeClassResponseDto timeClassResponseDto = timeClassService.createTimeClass(timeClassRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Time class created successfully", timeClassResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<TimeClassResponseDto>> updateTimeClass(String timeClassId,
      TimeClassRequestDto timeClassRequestDto) {
    log.info("Updating time class with id: {}", timeClassId);
    TimeClassResponseDto timeClassResponseDto = timeClassService.updateTimeClass(timeClassId, timeClassRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Time class updated successfully", timeClassResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<TimeClassResponseDto>> getTimeClass(String timeClassId) {
    log.info("Getting time class with id: {}", timeClassId);
    TimeClassResponseDto timeClassResponseDto = timeClassService.getTimeClass(timeClassId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Time class retrieved successfully", timeClassResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<TimeClassResponseDto>>> getTimeClassesByClassCode(
      String classCode) {
    log.info("Getting time classes by class code: {}", classCode);
    List<TimeClassResponseDto> timeClassResponseDtos = timeClassService.getTimeClassesByClassCode(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Time classes retrieved successfully", timeClassResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<TimeClassResponseDto>>> getTimeClassesByInstructorId(
      String instructorId) {
    log.info("Getting time classes by instructor id: {}", instructorId);
    List<TimeClassResponseDto> timeClassResponseDtos = timeClassService.getTimeClassesByInstructorId(instructorId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Time classes retrieved successfully", timeClassResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<TimeClassResponseDto>>> getTimeClassesByCourseCode(
      String courseCode) {
    log.info("Getting time classes by course code: {}", courseCode);
    List<TimeClassResponseDto> timeClassResponseDtos = timeClassService.getTimeClassesByCourseCode(courseCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Time classes retrieved successfully", timeClassResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteTimeClass(String timeClassId) {
    log.info("Deleting time class with id: {}", timeClassId);
    timeClassService.deleteTimeClass(timeClassId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Time class deleted successfully", null), HttpStatus.OK);
  }
}
