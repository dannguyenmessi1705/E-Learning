package com.didan.elearning.times_table.service;

import com.didan.elearning.times_table.dto.request.DateSchedulesFindRequestDto;
import com.didan.elearning.times_table.dto.request.DateSchedulesUpdateRequestDto;
import com.didan.elearning.times_table.dto.response.DateSchedulesResponseDto;
import com.didan.elearning.times_table.entity.DateSchedules;
import com.didan.elearning.times_table.entity.WeekSchedules;
import java.util.List;

public interface IDateSchedulesService {
  List<DateSchedulesResponseDto> createDateSchedules(WeekSchedules weekSchedules);
  List<DateSchedulesResponseDto> getDateSchedulesByWeekScheduleId(DateSchedulesFindRequestDto dateSchedulesFindRequestDto);
  DateSchedulesResponseDto getDateSchedulesByDate(String date);
  DateSchedulesResponseDto updateDateSchedules(DateSchedulesUpdateRequestDto dateSchedulesUpdateRequestDto);
  void deleteDateSchedules(String date);
}
