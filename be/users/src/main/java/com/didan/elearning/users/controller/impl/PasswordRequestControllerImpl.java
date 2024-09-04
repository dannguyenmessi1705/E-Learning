package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.controller.IPasswordRequestController;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.service.IPasswordRequestService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class PasswordRequestControllerImpl implements IPasswordRequestController {
  private final IPasswordRequestService passwordRequestService;
  @Override
  public ResponseEntity<GeneralResponse<String>> createPasswordRequest(
      @RequestBody
      @NotBlank
      @Email
      String email) {
    log.info("Creating password request...");
    passwordRequestService.createPasswordRequest(email);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Password request created successfully", null), HttpStatus.CREATED);
  }
}
