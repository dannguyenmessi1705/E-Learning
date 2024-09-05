package com.didan.elearning.users.service;

import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.RoleResponseDto;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import java.util.List;

public interface IUserService {
  CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto);
  UpdateUserDetailResponseDto updateUser(String userId, UpdateUserRequestDto updateUserRequestDto);
  RoleResponseDto assignRole(String userId, String roleName);
  RoleResponseDto unassignRole(String userId, String roleName);
  boolean deleteUser(String userId);
  List<UpdateUserDetailResponseDto> searchUser(String searchValue);
  UpdateUserDetailResponseDto getUserDetails(String userId);
  boolean activateUser(String userId);
  boolean deactivateUser(String userId);
  boolean changePassword(String token, String newPassword);
}
