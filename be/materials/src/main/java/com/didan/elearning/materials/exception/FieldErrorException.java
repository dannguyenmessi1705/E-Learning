package com.didan.elearning.materials.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FieldErrorException extends RuntimeException {
  public FieldErrorException(String message) {
    super(message);
  }

}
