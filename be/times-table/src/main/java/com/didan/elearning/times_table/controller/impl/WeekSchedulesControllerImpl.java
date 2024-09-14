package com.didan.elearning.times_table.controller.impl;

import com.didan.elearning.times_table.controller.IWeekSchedulesController;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import com.didan.elearning.times_table.dto.response.WeekSchedulesResponseDto;
import com.didan.elearning.times_table.service.IWeekSchedulesService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class WeekSchedulesControllerImpl implements IWeekSchedulesController {
  private final IWeekSchedulesService weekSchedulesService;

  @Override
  public ResponseEntity<GeneralResponse<List<WeekSchedulesResponseDto>>> createWeekSchedules(
      String semesterCode) {
    log.info("Creating week schedules for semester: {}", semesterCode);
    List<WeekSchedulesResponseDto> weekSchedules = weekSchedulesService.createWeekSchedules(semesterCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Create week schedules successfully", weekSchedules), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<WeekSchedulesResponseDto>> getWeekSchedulesDetails(
      String weekSchedulesId) {
    log.info("Getting week schedules details with id: {}", weekSchedulesId);
    WeekSchedulesResponseDto weekSchedules = weekSchedulesService.getWeekSchedulesDetails(weekSchedulesId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Week schedules details retrieved successfully", weekSchedules), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<WeekSchedulesResponseDto>>> getAllWeekSchedules(
      String semesterCode) {
    log.info("Getting all week schedules for semester: {}", semesterCode);
    List<WeekSchedulesResponseDto> weekSchedules = weekSchedulesService.getAllWeekSchedules(semesterCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "All week schedules retrieved successfully", weekSchedules), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteAllWeekSchedules(
      String semesterCode) {
    log.info("Deleting all week schedules for semester: {}", semesterCode);
    weekSchedulesService.deleteAllWeekSchedules(semesterCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "All week schedules deleted successfully", null), HttpStatus.NO_CONTENT);
  }
}
