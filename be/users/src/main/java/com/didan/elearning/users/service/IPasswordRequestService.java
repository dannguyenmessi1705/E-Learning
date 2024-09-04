package com.didan.elearning.users.service;

public interface IPasswordRequestService {
  String createPasswordRequest(String email);
  String validateToken(String token);
}
