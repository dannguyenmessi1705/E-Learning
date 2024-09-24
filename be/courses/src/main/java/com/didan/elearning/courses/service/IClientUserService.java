package com.didan.elearning.courses.service;

import com.didan.elearning.courses.dto.response.RoleResponseDto;
import com.didan.elearning.courses.dto.response.UpdateUserDetailResponseDto;

public interface IClientUserService {
  UpdateUserDetailResponseDto getUserDetail(String userId);
  UpdateUserDetailResponseDto getStudentByStudentCode(String studentCode);
  RoleResponseDto validateInstructor(String instructorId);
  RoleResponseDto validateAssistant(String assistantId);
}
