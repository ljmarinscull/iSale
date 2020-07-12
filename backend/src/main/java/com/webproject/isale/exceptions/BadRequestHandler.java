package com.webproject.isale.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class BadRequestHandler extends ResponseEntityExceptionHandler {

  /*  @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage)   ;
        });
        return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }
*/
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
      String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
      List<String> validationList = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,errorMessage,validationList);
      return new ResponseEntity<>(apiError, status);
  }
}
