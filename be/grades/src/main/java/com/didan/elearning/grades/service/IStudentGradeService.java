package com.didan.elearning.grades.service;

import com.didan.elearning.grades.dto.request.StudentGradeRequestDto;
import com.didan.elearning.grades.dto.response.StudentGradeResponseDto;
import java.util.List;

public interface IStudentGradeService {
  StudentGradeResponseDto createStudentGrade(StudentGradeRequestDto studentGradeRequestDto);
  StudentGradeResponseDto updateStudentGrade(String studentGradeId, StudentGradeRequestDto studentGradeRequestDto);
  List<StudentGradeResponseDto> getStudentGradeByStudentCode(String studentCode);
  List<StudentGradeResponseDto> getStudentGradeByClassCode(String classCode);
  StudentGradeResponseDto getGradeByStudentCodeAndClassCode(String studentCode, String classCode);
  void deleteStudentGrade(String studentGradeId);
}
