package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.controller.IUserController;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.users.service.IUserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@Slf4j
public class UserControllerImpl implements IUserController {
  private final IUserService userService;
  @Override
  public ResponseEntity<GeneralResponse<CreateUserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
    log.info("Creating user...");
    CreateUserResponseDto newUser = userService.createUser(createUserRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(),
        MessageConstant.USER_CREATED_SUCCESSFULLY, newUser), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> updateUser(@PathVariable("id") String userId, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
    log.info("Updating user...");
    UpdateUserDetailResponseDto updatedUser = userService.updateUser(userId, updateUserRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        "Updated user successfully", updatedUser), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> assignRole(String userId, String roleName) {
    log.info("Assigning role...");
    if (userService.assignRole(userId, roleName.toUpperCase())) {
      log.info("Role {} assigned successfully for userId: {}", roleName, userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          "Role " + roleName + " assigned successfully", "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("Role {} not assigned for userId: {}", roleName, userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          "Role " + roleName + " not assigned", "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> unassignRole(String userId, String roleName) {
    log.info("Unassigning role...");
    if (userService.unassignRole(userId, roleName.toUpperCase())) {
      log.info("Role {} unassigned successfully for userId: {}", roleName, userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          "Role " + roleName + " unassigned successfully", "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("Role {} not unassigned for userId: {}", roleName, userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          "Role " + roleName + " not unassigned", "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> deleteUser(String userId) {
    log.info("Deleting user...");
    if (userService.deleteUser(userId)) {
      log.info("User deleted successfully: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          "User deleted successfully", "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("User not deleted: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          "User can not deleted", "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<List<UpdateUserDetailResponseDto>>> searchUser(
      String searchValue) {
    List<UpdateUserDetailResponseDto> users = userService.searchUser(searchValue);
    if (users.isEmpty()) {
      log.info("No user found for search value: {}", searchValue);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NOT_FOUND.value(),
          "No user found for search value: " + searchValue, users), HttpStatus.NOT_FOUND);
    } else {
      log.info("Users found for search value: {}", searchValue);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          "Users found for search value: " + searchValue, users), HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> getUserDetails(
      String userId) {
    UpdateUserDetailResponseDto user = userService.getUserDetails(userId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        "User details found", user), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> activateUser(String userId) {
    if (userService.activateUser(userId)) {
      log.info("User activated successfully: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          "User activated successfully", "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("User not activated: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          "User can not activated", "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> deactivateUser(String userId) {
    if (userService.deactivateUser(userId)) {
      log.info("User deactivated successfully: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          "User deactivated successfully", "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("User not deactivated: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          "User can not deactivated", "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }
}
