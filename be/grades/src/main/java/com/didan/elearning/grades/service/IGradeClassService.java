package com.didan.elearning.grades.service;

import com.didan.elearning.grades.dto.request.GradeClassRequestDto;
import com.didan.elearning.grades.dto.response.GradeClassResponseDto;

public interface IGradeClassService {
  GradeClassResponseDto createGradeClass(GradeClassRequestDto gradeClassRequestDto);
  GradeClassResponseDto updateGradeClass(String gradeClassId, GradeClassRequestDto gradeClassRequestDto);
  GradeClassResponseDto getGradeByClassCode(String classCode);
  void deleteGradeClass(String gradeClassId);
}
