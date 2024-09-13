package com.didan.elearning.times_table.service;

import com.didan.elearning.times_table.dto.request.TimeClassRequestDto;
import com.didan.elearning.times_table.dto.response.TimeClassResponseDto;
import java.util.List;

public interface ITimeClassService {
  TimeClassResponseDto createTimeClass(TimeClassRequestDto timeClassRequestDto);
  TimeClassResponseDto updateTimeClass(String timeClassId, TimeClassRequestDto timeClassRequestDto);
  TimeClassResponseDto getTimeClass(String timeClassId);
  List<TimeClassResponseDto> getTimeClassesByClassCode(String classCode);
  List<TimeClassResponseDto> getTimeClassesByInstructorId(String instructorId);
  List<TimeClassResponseDto> getTimeClassesByCourseCode(String courseCode);
  void deleteTimeClass(String timeClassId);
}
