package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.controller.IUserController;
import com.didan.elearning.users.dto.request.ChangePasswordRequestDto;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.dto.response.RoleResponseDto;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.users.entity.Role;
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
  public ResponseEntity<GeneralResponse<CreateUserResponseDto>> createUser(CreateUserRequestDto createUserRequestDto) {
    log.info("Creating user...");
    CreateUserResponseDto newUser = userService.createUser(createUserRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(),
        MessageConstant.USER_CREATED_SUCCESSFULLY, newUser), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> updateUser(String userId, UpdateUserRequestDto updateUserRequestDto) {
    log.info("Updating user...");
    UpdateUserDetailResponseDto updatedUser = userService.updateUser(userId, updateUserRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        MessageConstant.USER_UPDATED_SUCCESSFULLY, updatedUser), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<RoleResponseDto>> assignRole(String userId, String roleName) {
    log.info("Assigning role...");
    RoleResponseDto roleResponseDto = userService.assignRole(userId, roleName.toUpperCase());
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        MessageConstant.ROLE_ASSIGNED_SUCCESSFULLY, roleResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<RoleResponseDto>> unassignRole(String userId, String roleName) {
    log.info("Unassigning role...");
    RoleResponseDto roleResponseDto = userService.unassignRole(userId, roleName.toUpperCase());
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        MessageConstant.ROLE_UNASSIGNED_SUCCESSFULLY, roleResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> deleteUser(String userId) {
    log.info("Deleting user...");
    if (userService.deleteUser(userId)) {
      log.info("User deleted successfully: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          MessageConstant.USER_DELETED_SUCCESSFULLY, "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("User not deleted: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          MessageConstant.USER_CANNOT_BE_DELETED, "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<List<UpdateUserDetailResponseDto>>> searchUser(
      String searchValue) {
    List<UpdateUserDetailResponseDto> users = userService.searchUser(searchValue);
    if (users.isEmpty()) {
      log.info("No user found for search value: {}", searchValue);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NOT_FOUND.value(),
          MessageConstant.USER_NOT_FOUND, users), HttpStatus.NOT_FOUND);
    } else {
      log.info("Users found for search value: {}", searchValue);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          MessageConstant.USER_FOUND, users), HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> getUserDetails(
      String userId) {
    UpdateUserDetailResponseDto user = userService.getUserDetails(userId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
        MessageConstant.USER_FOUND, user), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> activateUser(String userId) {
    if (userService.activateUser(userId)) {
      log.info("User activated successfully: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          MessageConstant.USER_ACTIVATED_SUCCESSFULLY, "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("User not activated: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          MessageConstant.USER_ACTIVATION_FAILED, "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> deactivateUser(String userId) {
    if (userService.deactivateUser(userId)) {
      log.info("User deactivated successfully: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          MessageConstant.USER_DEACTIVATED_SUCCESSFULLY, "userId: " + userId), HttpStatus.OK);
    } else {
      log.info("User not deactivated: {}", userId);
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          MessageConstant.USER_DEACTIVATION_FAILED, "userId: " + userId), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> changePassword(
      ChangePasswordRequestDto changePasswordRequestDto) {
    if (userService.changePassword(changePasswordRequestDto.getToken(), changePasswordRequestDto.getPassword())) {
      log.info("Password changed successfully");
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(),
          MessageConstant.PASSWORD_CHANGED_SUCCESSFULLY, ""), HttpStatus.OK);
    } else {
      log.info("Password not changed");
      return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(),
          MessageConstant.PASSWORD_CANNOT_BE_CHANGED, ""), HttpStatus.BAD_REQUEST);
    }
  }
}
