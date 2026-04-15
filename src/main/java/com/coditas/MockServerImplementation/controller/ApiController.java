package com.coditas.MockServerImplementation.controller;

import com.coditas.MockServerImplementation.dto.ApplicationResponse;
import com.coditas.MockServerImplementation.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
  private final ApiService apiService;

    @GetMapping("/profile")
    public ResponseEntity<ApplicationResponse<String>> getProfile(){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(apiService.getProfile());
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

}
