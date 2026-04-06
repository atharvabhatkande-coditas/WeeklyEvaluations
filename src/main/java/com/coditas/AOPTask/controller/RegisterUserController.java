package com.coditas.AOPTask.controller;

import com.coditas.AOPTask.DTO.ApplicationResponse;
import com.coditas.AOPTask.entity.RegisterUser;
import com.coditas.AOPTask.entity.Resume;
import com.coditas.AOPTask.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterUserController {
    private final RegisterUserService registerUserService;


    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse<String>> registerUser(@RequestBody RegisterUser user){

        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(registerUserService.registerUser(user));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse<String>> loginUser(@RequestBody RegisterUser user){

        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(registerUserService.loginUser(user));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }
}
