package com.coditas.AOPTask.exception;

import com.coditas.AOPTask.DTO.ApplicationResponse;
import com.coditas.AOPTask.DTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleNotFoundException(NotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoStock.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleOutOfStockException(NoStock e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleValidationException(ValidationException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.NOT_ACCEPTABLE);
    }


}
