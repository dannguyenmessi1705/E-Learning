package com.didan.elearning.courses.service.impl;

import com.didan.elearning.courses.constants.MessageConstants;
import com.didan.elearning.courses.constants.MessageConstants.Status;
import com.didan.elearning.courses.constants.RoleConstants;
import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.RoleResponseDto;
import com.didan.elearning.courses.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.courses.exception.ResourceNotFoundException;
import com.didan.elearning.courses.service.IClientUserService;
import com.didan.elearning.courses.service.client.UsersFeignClient;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ClientUserServiceImpl implements IClientUserService {
  private final UsersFeignClient usersFeignClient;

  @Override
  public UpdateUserDetailResponseDto getUserDetail(String userId) {
    try {
      ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> user = usersFeignClient.getUserDetails(userId);
      if (!Objects.equals(Objects.requireNonNull(user.getBody()).getStatusCode(), Status.SUCCESS)) {
        log.info("User with id {} not found", userId);
        throw new ResourceNotFoundException(String.format(MessageConstants.USER_NOT_FOUND, userId));
      }
      return user.getBody().getData();
    } catch (Exception e) {
      throw new ResourceNotFoundException(String.format(MessageConstants.USER_NOT_FOUND, userId));
    }
  }

  @Override
  public UpdateUserDetailResponseDto getStudentByStudentCode(String studentCode) {
      try {
        ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> student = usersFeignClient.getStudentByStudentCode(
            studentCode);
        if (!Objects.equals(Objects.requireNonNull(student.getBody()).getStatusCode(), Status.SUCCESS)) {
          log.info("Student with code {} not found", studentCode);
          throw new ResourceNotFoundException(String.format(MessageConstants.STUDENT_NOT_FOUND,
              studentCode));
        }
        return student.getBody().getData();
      } catch (Exception e) {
        throw new ResourceNotFoundException(String.format(MessageConstants.STUDENT_NOT_FOUND,
            studentCode));
      }
    }
  @Override
  public RoleResponseDto validateInstructor(String instructorId) {
    try {
      ResponseEntity<GeneralResponse<RoleResponseDto>> instructorResponse = usersFeignClient.getRoleForUser(instructorId);
      if (!Objects.equals(Objects.requireNonNull(instructorResponse.getBody()).getStatusCode(), Status.SUCCESS)) {
        log.error("Error getting role for instructor with id {}", instructorId);
        throw new ResourceNotFoundException("Error getting role for instructor with id " + instructorId);
      }
      List<String> roles = Arrays.stream(Objects.requireNonNull(instructorResponse.getBody().getData()).getRoleName()).toList();
      if (!roles.contains(RoleConstants.INSTRUCTOR)) {
        log.error("User with id {} is not an instructor", instructorId);
        throw new ResourceNotFoundException("User with id " + instructorId + " is not an instructor");
      }
      return instructorResponse.getBody().getData();
    } catch (Exception e) {
      log.error("Instructor with id {} not found", instructorId);
      throw new ResourceNotFoundException(String.format(MessageConstants.INSTRUCTOR_NOT_FOUND, instructorId));
    }
  }

  @Override
  public RoleResponseDto validateAssistant(String assistantId) {
    try {
      ResponseEntity<GeneralResponse<RoleResponseDto>> assistantResponse = usersFeignClient.getRoleForUser(assistantId);
      if (!Objects.equals(Objects.requireNonNull(assistantResponse.getBody()).getStatusCode(), Status.SUCCESS)) {
        log.error("Error getting role for instructor with id {}", assistantId);
        throw new ResourceNotFoundException("Error getting role for instructor with id " + assistantId);
      }
      List<String> roles = Arrays.stream(Objects.requireNonNull(assistantResponse.getBody().getData()).getRoleName()).toList();
      if (!roles.contains(RoleConstants.INSTRUCTOR)) {
        log.error("User with id {} is not an assistant", assistantId);
        throw new ResourceNotFoundException("User with id " + assistantId + " is not an assistant");
      }
      return assistantResponse.getBody().getData();
    } catch (Exception e) {
      log.error("Assistant with id {} not found", assistantId);
      throw new ResourceNotFoundException(String.format(MessageConstants.ASSISTANT_NOT_FOUND, assistantId));
    }
  }
}
