package com.didan.elearning.courses.service;

import com.didan.elearning.courses.dto.request.SemesterRequestDto;
import com.didan.elearning.courses.dto.response.SemesterResponseDto;
import java.util.List;

public interface ISemesterService {
  SemesterResponseDto createSemester(SemesterRequestDto semesterRequestDto);
  List<SemesterResponseDto> getAllSemesters();
}
