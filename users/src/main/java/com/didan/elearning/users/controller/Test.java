package com.didan.elearning.users.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
  @Value("${name.didan}")
  private String name;

  @GetMapping("/get")
  public ResponseEntity<String> get() {
    return ResponseEntity.ok(name);
  }
}
