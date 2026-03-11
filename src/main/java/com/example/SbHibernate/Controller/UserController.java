package com.example.SbHibernate.Controller;

import com.example.SbHibernate.Entity.User;
import com.example.SbHibernate.Exception.ApplicationResponse;
import com.example.SbHibernate.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse<String>>registerUser(@Valid @RequestBody User user){
        userService.registerUser(user);
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>("User Registered Successfully");
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse<String>>loginUser(@RequestBody Map<String,String>loginRequest){
        userService.userLogin(loginRequest.get("userNameorEmail"),loginRequest.get("password"));
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>("User Logged In successfully");
        return new ResponseEntity<>(applicationResponse,HttpStatus.OK);
    }
}
