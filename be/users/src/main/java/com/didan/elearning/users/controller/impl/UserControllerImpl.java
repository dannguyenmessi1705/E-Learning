package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.controller.IUserController;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.users.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class UserControllerImpl implements IUserController {
  private final IUserService userService;
  @Override
  public ResponseEntity<GeneralResponse<CreateUserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
    CreateUserResponseDto newUser = userService.createUser(createUserRequestDto);

    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(),
        "Created user successfully", newUser), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> updateUser(@PathVariable("id") String userId, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
    UpdateUserDetailResponseDto updatedUser = userService.updateUser(userId, updateUserRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        "Updated user successfully", updatedUser), HttpStatus.OK);
  }
}
