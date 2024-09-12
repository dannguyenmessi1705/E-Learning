package com.didan.elearning.grades.service;

import com.didan.elearning.grades.dto.request.WeightGradeRequestDto;
import com.didan.elearning.grades.dto.response.WeightGradeResponseDto;

public interface IWeightGradeService {
  WeightGradeResponseDto createWeightGradeForCourse(WeightGradeRequestDto weightGradeRequestDto);
  WeightGradeResponseDto updateWeightGradeForCourse(String weightGradeId, WeightGradeRequestDto weightGradeRequestDto);
  WeightGradeResponseDto getWeightGradeByCourseCode(String courseCode);
  void deleteWeightGrade(String weightGradeId);
}
