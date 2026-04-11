package com.coditas.NewJWTAssignment.controller;

import com.coditas.NewJWTAssignment.dto.*;
import com.coditas.NewJWTAssignment.service.UserRegistrationService;
import com.coditas.NewJWTAssignment.util.JwtUtil;
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
    private final UserRegistrationService userRegistrationService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse<String>>registerUser(@Valid @RequestBody UserDto userDto){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(userRegistrationService.registerNewUser(userDto));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse<AuthResponse>>generateToken(@RequestBody AuthRequest authRequest){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            ApplicationResponse<AuthResponse>applicationResponse=new ApplicationResponse<>(jwtUtil.generateToken(authRequest.getUsername(),authRequest.getPassword()));
            return new ResponseEntity<>(applicationResponse, HttpStatus.OK);

    }

}
