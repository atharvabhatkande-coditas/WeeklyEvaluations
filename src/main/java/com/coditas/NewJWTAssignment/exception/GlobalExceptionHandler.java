package com.coditas.NewJWTAssignment.exception;

import com.coditas.NewJWTAssignment.dto.ApplicationResponse;
import com.coditas.NewJWTAssignment.dto.ErrorResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleAlreadyExistException(AlreadyExistException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationResponse<List<ErrorResponse>>> handleValidationException(MethodArgumentNotValidException e) {
        List<String>listOfValidationErrors=e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();
       // List<ErrorResponse>errorResponseList=listOfValidationErrors.forEach(error->new ErrorResponse(error,LocalDateTime.now(),HttpStatus.NOT_ACCEPTABLE.value()));

        ErrorResponse error = new ErrorResponse("Validation Failed", LocalDateTime.now(), HttpStatus.CONFLICT.value());
        ApplicationResponse<List<ErrorResponse>> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleValidationException(ValidationException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleusernamrNotFoundException(UsernameNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleAuthenticationException(AuthenticationException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleNotFoundException(NotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApplicationResponse<ErrorResponse>> handleJwtException(JwtException  e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value());
        ApplicationResponse<ErrorResponse> applicationResponse = new ApplicationResponse<>(List.of(error));
        return new ResponseEntity<>(applicationResponse, HttpStatus.UNAUTHORIZED);
    }







}
