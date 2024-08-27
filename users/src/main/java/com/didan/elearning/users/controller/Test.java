package com.didan.elearning.users.controller;

import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
public class Test {
  private UserRepository userRepository;

  @GetMapping("/get")
  public ResponseEntity<List<User>> get() {
    List<User> user = userRepository.findAll();
    log.info(String.valueOf(user.get(0).getRoles().size()));
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
