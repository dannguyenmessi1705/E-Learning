package com.didan.elearning.times_table.service;

import com.didan.elearning.times_table.dto.request.ClassInDateRequestDto;
import com.didan.elearning.times_table.dto.response.ClassInDateResponseDto;
import java.util.List;

public interface IClassInDateService {
  ClassInDateResponseDto createClassInDate(ClassInDateRequestDto classInDateRequestDto);
  ClassInDateResponseDto updateClassInDate(String classInDateId, ClassInDateRequestDto classInDateRequestDto);
  List<ClassInDateResponseDto> getAllClassInDateByClassCodeAndDateScheduleId(String classCode, String date);
  void deleteClassInDate(String classInDateId);
}
