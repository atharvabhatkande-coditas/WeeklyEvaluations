package com.coditas.NewJWTAssignment.controller;

import com.coditas.NewJWTAssignment.dto.*;
import com.coditas.NewJWTAssignment.service.ApiService;
import com.coditas.NewJWTAssignment.service.UserRegistrationService;
import com.coditas.NewJWTAssignment.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class JwtController {
    private final JwtUtil jwtUtil;
    private final ApiService apiService;

    @PostMapping("/refresh")
    public ResponseEntity<ApplicationResponse<String>>generateToken(@RequestBody AuthRequest authRequest){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(jwtUtil.generateAccessToken(authRequest));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);

    }

    @PostMapping("/logout")
    public ResponseEntity<ApplicationResponse<String>>logout(@RequestBody AuthRequest authRequest){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(apiService.logout(authRequest));
        return new ResponseEntity<>(applicationResponse,HttpStatus.OK);
    }



}
