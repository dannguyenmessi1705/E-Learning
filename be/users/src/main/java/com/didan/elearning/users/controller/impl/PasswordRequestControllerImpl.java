package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.controller.IPasswordRequestController;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.service.IPasswordRequestService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@Slf4j
public class PasswordRequestControllerImpl implements IPasswordRequestController {
  private final IPasswordRequestService passwordRequestService;
  @Override
  public ResponseEntity<GeneralResponse<String>> createPasswordRequest(String email) {
    log.info("Creating password request...");
    String token = passwordRequestService.createPasswordRequest(email);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), MessageConstant.PASSWORD_REQUEST_CREATED_SUCCESSFULLY, "token: " + token), HttpStatus.CREATED);
  }
}
