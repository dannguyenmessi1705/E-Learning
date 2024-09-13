package com.didan.elearning.times_table.service.impl;

import com.didan.elearning.times_table.constant.BooleanConstant;
import com.didan.elearning.times_table.constant.DateConstant;
import com.didan.elearning.times_table.constant.MessageConstant;
import com.didan.elearning.times_table.dto.request.DateSchedulesFindRequestDto;
import com.didan.elearning.times_table.dto.request.DateSchedulesUpdateRequestDto;
import com.didan.elearning.times_table.dto.response.DateSchedulesResponseDto;
import com.didan.elearning.times_table.entity.DateSchedules;
import com.didan.elearning.times_table.entity.WeekSchedules;
import com.didan.elearning.times_table.exception.FieldErrorException;
import com.didan.elearning.times_table.exception.ResourceNotFoundException;
import com.didan.elearning.times_table.repository.DateSchedulesRepository;
import com.didan.elearning.times_table.service.IDateSchedulesService;
import com.didan.elearning.times_table.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DateSchedulesServiceImpl implements IDateSchedulesService {
  private final DateSchedulesRepository dateSchedulesRepository;

  @Override
  public List<DateSchedulesResponseDto> createDateSchedules(WeekSchedules weekSchedules) {
    LocalDate startDate = weekSchedules.getStartWeekDate(); // Monday
    LocalDate endDate = weekSchedules.getEndWeekDate(); // Sunday
    List<DateSchedulesResponseDto> dateSchedulesResponses = new ArrayList<>();
    while (startDate.isBefore(endDate)) {
      DateSchedules dateSchedules = DateSchedules.builder()
          .weekSchedules(weekSchedules)
          .day(startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
          .date(startDate)
          .isDayOff(String.valueOf(startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals(
              DateConstant.SUNDAY)).toLowerCase())
          .build();
      dateSchedulesRepository.save(dateSchedules);
      dateSchedulesResponses.add(MapperUtils.map(dateSchedules, DateSchedulesResponseDto.class));
      startDate = startDate.plusDays(1);
    }
    log.info("Date schedules created successfully for week schedule: {}", weekSchedules.getWeekScheduleId());
    return dateSchedulesResponses;
  }

  @Override
  public List<DateSchedulesResponseDto> getDateSchedulesByWeekScheduleId(
      DateSchedulesFindRequestDto dateSchedulesFindRequestDto) {
    List<DateSchedules> dateSchedules = dateSchedulesRepository.findDateSchedulesByWeekSchedules_WeekNumberAndWeekSchedules_SemesterCode(
        Integer.parseInt(dateSchedulesFindRequestDto.getWeekNumber()), dateSchedulesFindRequestDto.getSemesterCode());
    if (dateSchedules.isEmpty()) {
      log.error("Date schedules not found with week number: {} and semester code: {}",
          dateSchedulesFindRequestDto.getWeekNumber(), dateSchedulesFindRequestDto.getSemesterCode());
      return List.of();
    }
    log.info("Date schedules found successfully with week number: {} and semester code: {}",
        dateSchedulesFindRequestDto.getWeekNumber(), dateSchedulesFindRequestDto.getSemesterCode());
    return MapperUtils.mapList(dateSchedules, DateSchedulesResponseDto.class);
  }

  @Override
  @Transactional
  @Modifying
  public DateSchedulesResponseDto updateDateSchedules(DateSchedulesUpdateRequestDto dateSchedulesUpdateRequestDto) {
    DateSchedules dateSchedules = dateSchedulesRepository.findDateSchedulesByDate(LocalDate.parse(dateSchedulesUpdateRequestDto.getDate()))
        .orElseThrow(() -> {
          log.error("Date schedule not found with date: {}", dateSchedulesUpdateRequestDto.getDate());
          return new ResourceNotFoundException(String.format(MessageConstant.DATE_SCHEDULE_NOT_FOUND, dateSchedulesUpdateRequestDto.getDate()));
        });
    if (!dateSchedulesUpdateRequestDto.getIsDayOff().equalsIgnoreCase(BooleanConstant.FALSE) && !dateSchedulesUpdateRequestDto.getIsDayOff().equalsIgnoreCase(BooleanConstant.TRUE)) {
      log.error("Invalid isDayOff value: {}", dateSchedulesUpdateRequestDto.getIsDayOff());
      throw new FieldErrorException("The isDayOff value is invalid, must be true or false");
    }
    dateSchedules.setIsDayOff(dateSchedulesUpdateRequestDto.getIsDayOff().toLowerCase());
    dateSchedulesRepository.save(dateSchedules);
    log.info("Date schedule updated successfully with date: {}", dateSchedulesUpdateRequestDto.getDate());
    return MapperUtils.map(dateSchedules, DateSchedulesResponseDto.class);
  }

  @Override
  @Transactional
  @Modifying
  public void deleteDateSchedules(String date) {
    LocalDate findDate = LocalDate.parse(date);
    dateSchedulesRepository.findDateSchedulesByDate(findDate).ifPresentOrElse(dateSchedules -> {
      dateSchedulesRepository.delete(dateSchedules);
      log.info("Date schedule deleted successfully with date: {}", date);
    }, () -> {
      log.error("Date schedule not found with date: {}", date);
      throw new ResourceNotFoundException(String.format(MessageConstant.DATE_SCHEDULE_NOT_FOUND, date));
    });
  }

  @Override
  public DateSchedulesResponseDto getDateSchedulesByDate(String date) {
    DateSchedules dateSchedules = dateSchedulesRepository.findDateSchedulesByDate(LocalDate.parse(date))
        .orElseThrow(() -> {
          log.error("Date schedule not found with date: {}", date);
          return new ResourceNotFoundException(String.format(MessageConstant.DATE_SCHEDULE_NOT_FOUND, date));
        });
    log.info("Date schedule found successfully with date: {}", date);
    return MapperUtils.map(dateSchedules, DateSchedulesResponseDto.class);
  }
}
