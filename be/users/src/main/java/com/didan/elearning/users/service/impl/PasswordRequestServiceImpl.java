package com.didan.elearning.users.service.impl;

import com.didan.elearning.users.entity.PasswordRequest;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.exception.FieldErrorException;
import com.didan.elearning.users.exception.ResourceNotFoundException;
import com.didan.elearning.users.repository.PasswordRequestRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.service.IPasswordRequestService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordRequestServiceImpl implements IPasswordRequestService {
  private final UserRepository userRepository;
  private final PasswordRequestRepository passwordRequestRepository;
  @Override
  public String createPasswordRequest(String email) {
    User user = userRepository.findUserByEmail(email).orElseThrow(() -> {
      log.error("User with email {} not found", email);
      return new ResourceNotFoundException("User with email " + email + " not found");
    });

    PasswordRequest passwordRequest = passwordRequestRepository.findPasswordRequestByUser_UserId(user.getUserId()).orElse(new PasswordRequest());
    String token = RandomStringUtils.random(20, true, true);

    if (StringUtils.hasText(passwordRequest.getRequestId())) {
      passwordRequest.setToken(token);
      passwordRequest.setExpiredAt(LocalDateTime.now().plusMinutes(10));
    } else {
      passwordRequest.setToken(token);
      passwordRequest.setUser(user);
    }
    passwordRequestRepository.save(passwordRequest);
    log.info("Password request created successfully for user with email {}", email);
    return token;
  }

  @Override
  public String validateToken(String token) {
    PasswordRequest passwordRequest = passwordRequestRepository.findPasswordRequestByToken(token).orElseThrow(() -> {
      log.error("Password request with token {} not found", token);
      return new ResourceNotFoundException("Password request with token " + token + " not found");
    });
    if (passwordRequest.getExpiredAt().isBefore(LocalDateTime.now())) {
      log.error("Password request with token {} has expired", token);
      throw new FieldErrorException("Password request with token " + token + " has expired");
    }
    log.info("Token {} validated successfully", token);
    passwordRequest.setToken(null);
    passwordRequest.setExpiredAt(null);
    passwordRequestRepository.save(passwordRequest);
    return passwordRequest.getUser().getUserId();
  }
}
