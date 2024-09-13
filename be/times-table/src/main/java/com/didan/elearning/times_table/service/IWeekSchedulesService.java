package com.didan.elearning.times_table.service;

import com.didan.elearning.times_table.dto.response.WeekSchedulesResponseDto;
import java.util.List;

public interface IWeekSchedulesService {
  List<WeekSchedulesResponseDto> createWeekSchedules(String semesterCode);
  WeekSchedulesResponseDto getWeekSchedulesDetails(String weekSchedulesId);
  List<WeekSchedulesResponseDto> getAllWeekSchedules(String semesterCode);
  void deleteAllWeekSchedules(String semesterCode);
}
