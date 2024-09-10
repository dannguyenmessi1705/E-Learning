package com.didan.elearning.attendances.service;

import com.didan.elearning.attendances.dto.request.RecordUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.RecordResponseDto;
import com.didan.elearning.attendances.entity.Attendances;
import java.util.List;

public interface IRecordService {
  void createRecordForAttendance(Attendances attendances);
  RecordResponseDto updateRecordForAttendance(String recordId, RecordUpdateRequestDto recordUpdateRequestDto);
  void deleteRecordForAttendance(String recordId);
  RecordResponseDto getRecordDetailsForAttendance(String recordId);
  List<RecordResponseDto> getAllRecordsForAttendance(String attendanceId);
}
