package com.didan.elearning.users.service.impl;

import com.didan.elearning.users.entity.PasswordRequest;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.exception.ResourceNotFoundException;
import com.didan.elearning.users.repository.PasswordRequestRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.service.IPasswordRequestService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordRequestServiceImpl implements IPasswordRequestService {
  private final UserRepository userRepository;
  private final PasswordRequestRepository passwordRequestRepository;
  @Override
  public void createPasswordRequest(String email) {
    Optional<User> user = userRepository.findUserByEmail(email);
    if (user.isEmpty()) {
      log.error("User with email {} not found", email);
      throw new ResourceNotFoundException("User with email " + email + " not found");
    }
    PasswordRequest passwordRequest = new PasswordRequest();
    String token = RandomStringUtils.random(20, true, true);
    passwordRequest.setToken(token);
    passwordRequest.setUser(user.get());
    passwordRequestRepository.save(passwordRequest);
  }
}
