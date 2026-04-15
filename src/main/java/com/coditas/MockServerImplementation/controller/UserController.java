package com.coditas.MockServerImplementation.controller;

import com.coditas.MockServerImplementation.dto.ApplicationResponse;
import com.coditas.MockServerImplementation.dto.RequestDto;
import com.coditas.MockServerImplementation.dto.ResponseDto;
import com.coditas.MockServerImplementation.entity.Users;
import com.coditas.MockServerImplementation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse<String>>registerUser(@RequestBody Users user){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(userService.registerUser(user));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse<ResponseDto>>loginUser(@RequestBody Users user){
        ApplicationResponse<ResponseDto>applicationResponse=new ApplicationResponse<>(userService.loginUser(user));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @PostMapping("getToken")
    public ResponseEntity<ApplicationResponse<ResponseDto>>generateToken(@RequestBody RequestDto requestDto){
        ApplicationResponse<ResponseDto>applicationResponse=new ApplicationResponse<>(userService.generateToken(requestDto));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }



}
