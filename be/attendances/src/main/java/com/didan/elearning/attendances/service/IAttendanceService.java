package com.didan.elearning.attendances.service;

import com.didan.elearning.attendances.dto.request.AttendanceRequestDto;
import com.didan.elearning.attendances.dto.request.AttendanceUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.AttendanceResponseDto;
import java.util.List;

public interface IAttendanceService {
  AttendanceResponseDto createAttendanceForClass(AttendanceRequestDto attendanceRequestDto);
  AttendanceResponseDto getAttendanceDetailsForClass(String attendanceId);
  List<AttendanceResponseDto> getAllAttendancesForClass(String classCode);
  AttendanceResponseDto updateAttendanceForClass(String attendanceId, AttendanceUpdateRequestDto attendanceUpdateRequestDto);
  void deleteAttendanceForClass(String attendanceId);
}
