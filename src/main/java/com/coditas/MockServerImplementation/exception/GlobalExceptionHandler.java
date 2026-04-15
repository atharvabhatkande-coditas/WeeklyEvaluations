package com.coditas.MockServerImplementation.exception;

import com.coditas.MockServerImplementation.dto.ApplicationResponse;
import com.coditas.MockServerImplementation.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>>handleNotFoundException(NotFoundException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        ApplicationResponse<ErrorResponse> applicationResponse= new ApplicationResponse<>(List.of(errorResponse));
        return new ResponseEntity<>(applicationResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>>handleAlreadyExistException(AlreadyExistException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT.value());
        ApplicationResponse<ErrorResponse> applicationResponse= new ApplicationResponse<>(List.of(errorResponse));
        return new ResponseEntity<>(applicationResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>>handleValidationException(ValidationException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value());
        ApplicationResponse<ErrorResponse> applicationResponse= new ApplicationResponse<>(List.of(errorResponse));
        return new ResponseEntity<>(applicationResponse,HttpStatus.UNAUTHORIZED);
    }

}
