package com.didan.elearning.users.service;

import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;

public interface IUserService {
  public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto);
  public UpdateUserDetailResponseDto updateUser(String userId, UpdateUserRequestDto updateUserRequestDto);
}
