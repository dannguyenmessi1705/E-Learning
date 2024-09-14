package com.didan.elearning.times_table.controller.impl;

import com.didan.elearning.times_table.controller.IDateScheduleController;
import com.didan.elearning.times_table.dto.request.DateSchedulesFindRequestDto;
import com.didan.elearning.times_table.dto.request.DateSchedulesUpdateRequestDto;
import com.didan.elearning.times_table.dto.response.DateSchedulesResponseDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import com.didan.elearning.times_table.service.IDateSchedulesService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class DateScheduleControllerImpl implements IDateScheduleController {
  private final IDateSchedulesService dateSchedulesService;

  @Override
  public ResponseEntity<GeneralResponse<List<DateSchedulesResponseDto>>> getDateSchedulesByWeekScheduleId(
      DateSchedulesFindRequestDto dateSchedulesFindRequestDto) {
    log.info("Get date schedules by week schedule");
    List<DateSchedulesResponseDto> dateSchedulesResponseDtos = dateSchedulesService.getDateSchedulesByWeekScheduleId(dateSchedulesFindRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get date schedules by week schedule", dateSchedulesResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<DateSchedulesResponseDto>> getDateSchedulesByDate(
      String date) {
    log.info("Get date schedules by date");
    DateSchedulesResponseDto dateSchedulesResponseDto = dateSchedulesService.getDateSchedulesByDate(date);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get date schedules by date", dateSchedulesResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<DateSchedulesResponseDto>> updateDateSchedules(
      DateSchedulesUpdateRequestDto dateSchedulesUpdateRequestDto) {
    log.info("Update date schedules");
    DateSchedulesResponseDto dateSchedulesResponseDto = dateSchedulesService.updateDateSchedules(dateSchedulesUpdateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Update date schedules", dateSchedulesResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteDateSchedules(String date) {
    log.info("Delete date schedules");
    dateSchedulesService.deleteDateSchedules(date);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Delete date schedules", null), HttpStatus.OK);
  }
}
