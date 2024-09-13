package com.didan.elearning.times_table.service.impl;

import com.didan.elearning.times_table.constant.MessageConstant;
import com.didan.elearning.times_table.dto.response.WeekSchedulesResponseDto;
import com.didan.elearning.times_table.entity.WeekSchedules;
import com.didan.elearning.times_table.exception.ResourceNotFoundException;
import com.didan.elearning.times_table.repository.WeekSchedulesRepository;
import com.didan.elearning.times_table.service.IDateSchedulesService;
import com.didan.elearning.times_table.service.IWeekSchedulesService;
import com.didan.elearning.times_table.utils.MapperUtils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WeekSchedulesServiceImpl implements IWeekSchedulesService {

  private final WeekSchedulesRepository weekSchedulesRepository;
  private final IDateSchedulesService dateSchedulesService;

  @Override
  public List<WeekSchedulesResponseDto> createWeekSchedules(String semesterCode) {
    /**
     * OpenFeign call to course service to get semester details
     * if semester not found throw ResourceNotFoundException
     * if exist, assign semeserCode, startDate, endDate of semester to WeekSchedulesResponseDto
     */
    LocalDate startDate = LocalDate.parse("2021-09-01"); // get from semester.startDate()
    LocalDate endDate = LocalDate.parse("2021-12-30"); // get from semester.endDate()
    List<WeekSchedulesResponseDto> weekSchedules = new ArrayList<>();
    int week = 1;
    while (startDate.isBefore(endDate)) {
      // create week schedule
      WeekSchedules weekSchedule = WeekSchedules.builder()
          .semesterCode(semesterCode)
          .weekNumber(week)
          .startWeekDate(startDate) // Get start of week date (Default Monday from CourseService)
          .endWeekDate(startDate.with(DayOfWeek.SUNDAY)) // Get end of week date (Sunday)
          .build();
      startDate = startDate.plusWeeks(1);
      week++;
      weekSchedulesRepository.save(weekSchedule);
      dateSchedulesService.createDateSchedules(weekSchedule);
      weekSchedules.add(MapperUtils.map(weekSchedule, WeekSchedulesResponseDto.class));
    }
    log.info("Week schedules created successfully");
    return weekSchedules;
  }

  @Override
  public WeekSchedulesResponseDto getWeekSchedulesDetails(String weekSchedulesId) {
    WeekSchedules weekSchedules = weekSchedulesRepository.findById(weekSchedulesId)
        .orElseThrow(() -> {
          log.error("Week schedule not found with id: {}", weekSchedulesId);
          return new ResourceNotFoundException(
              String.format(MessageConstant.WEEK_SCHEDULE_NOT_FOUND, weekSchedulesId));
        });
    log.info("Week schedule found with id: {}", weekSchedulesId);
    return MapperUtils.map(weekSchedules, WeekSchedulesResponseDto.class);
  }

  @Override
  public List<WeekSchedulesResponseDto> getAllWeekSchedules(String semesterCode) {
    /**
     * OpenFeign call to course service to get semester details
     * if semester not found throw ResourceNotFoundException
     * if exist, assign semeserCode, startDate, endDate of semester to WeekSchedulesResponseDto
     */
    List<WeekSchedules> weekSchedules = weekSchedulesRepository.findWeekSchedulesBySemesterCodeIgnoreCase(
        semesterCode);
    if (weekSchedules.isEmpty()) {
      log.error("Week schedules not found for semester: {}", semesterCode);
      return List.of();
    }
    log.info("Week schedules found for semester: {}", semesterCode);
    return MapperUtils.mapList(weekSchedules, WeekSchedulesResponseDto.class);
  }

  @Override
  public void deleteAllWeekSchedules(String semesterCode) {
    /**
     * OpenFeign call to course service to get semester details
     * if semester not found throw ResourceNotFoundException
     * if exist, assign semeserCode, startDate, endDate of semester to WeekSchedulesResponseDto
     */
    weekSchedulesRepository.deleteAllBySemesterCodeIgnoreCase(semesterCode);
    log.info("Week schedules deleted successfully for semester: {}", semesterCode);
  }
}
