package com.didan.elearning.materials.exception;

import com.didan.elearning.materials.dto.error.ErrorDto;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> validationErrorsList = ex.getBindingResult().getAllErrors();

    validationErrorsList.forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      validationErrors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(
        new ErrorDto(request.getDescription(false), HttpStatus.BAD_REQUEST.value(),
            validationErrors,
            LocalDateTime.now()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDto> handleAllExceptions(Exception ex, WebRequest request) {
    return new ResponseEntity<>(
        new ErrorDto(request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            LocalDateTime.now()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceAlreadyExistException.class)
  public final ResponseEntity<ErrorDto> handleUserAlreadyExistException(
      ResourceAlreadyExistException ex, WebRequest request) {
    return new ResponseEntity<>(
        new ErrorDto(request.getDescription(false), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
            LocalDateTime.now()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ErrorDto> handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    return new ResponseEntity<>(
        new ErrorDto(request.getDescription(false), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
            LocalDateTime.now()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FieldErrorException.class)
  public final ResponseEntity<ErrorDto> handleFieldErrorException(FieldErrorException ex,
      WebRequest request) {
    return new ResponseEntity<>(
        new ErrorDto(request.getDescription(false), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
            LocalDateTime.now()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ErrorActionException.class)
  public final ResponseEntity<ErrorDto> handleErrorActionException(ErrorActionException ex,
      WebRequest request) {
    return new ResponseEntity<>(
        new ErrorDto(request.getDescription(false), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
            LocalDateTime.now()), HttpStatus.BAD_REQUEST);
  }
}
