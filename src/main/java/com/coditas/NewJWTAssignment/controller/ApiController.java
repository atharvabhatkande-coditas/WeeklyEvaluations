package com.coditas.NewJWTAssignment.controller;

import com.coditas.NewJWTAssignment.dto.ApplicationResponse;
import com.coditas.NewJWTAssignment.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/user/profile")
    public ResponseEntity<ApplicationResponse<String>> userProfile(){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(apiService.profile());
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<ApplicationResponse<String>> adminDashboard(){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(apiService.dashboard());
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @GetMapping("/private")
    public ResponseEntity<ApplicationResponse<String>> privateApi(){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>("Authenticated using Oauth");
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }


}
