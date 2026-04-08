package com.coditas.JWTAssignment.controller;

import com.coditas.JWTAssignment.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/generate-token")
    public String generateToken(@RequestParam String username){
        return jwtUtil.generateToken(username);
    }
}
