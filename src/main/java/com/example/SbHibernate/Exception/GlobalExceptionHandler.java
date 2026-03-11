package com.example.SbHibernate.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>>handleUserExistException(UserAlreadyExistsException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT.value());
        ApplicationResponse<ErrorResponse>applicationResponse=new ApplicationResponse<>(List.of(errorResponse));

        return new ResponseEntity<>(applicationResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleNotValidData(MethodArgumentNotValidException e){
        List<String> listOfValidationErrors=e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse=new ErrorResponse("Request Validation failed",LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),listOfValidationErrors);
        ApplicationResponse<ErrorResponse>applicationResponse=new ApplicationResponse<>(List.of(errorResponse));
        return new ResponseEntity<>(applicationResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserCredentialsNotValidException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>>handleInvalidCredentialsException(UserCredentialsNotValidException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value());
        ApplicationResponse<ErrorResponse>applicationResponse=new ApplicationResponse<>(List.of(errorResponse));
        return new ResponseEntity<>(applicationResponse, HttpStatus.UNAUTHORIZED);

    }
}
