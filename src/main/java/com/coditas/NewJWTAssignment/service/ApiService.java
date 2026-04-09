package com.coditas.NewJWTAssignment.service;

import org.springframework.stereotype.Service;

@Service
public class ApiService {

   public String profile(){
       return "User Profile ";
   }

   public String dashboard(){
       return "Admin Dashboard";
   }
}
