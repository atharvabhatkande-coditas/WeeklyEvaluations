package com.coditas.NewJWTAssignment.service;

import com.coditas.NewJWTAssignment.dto.AuthRequest;
import com.coditas.NewJWTAssignment.entity.RefreshToken;
import com.coditas.NewJWTAssignment.exception.NotFoundException;
import com.coditas.NewJWTAssignment.repository.RefreshTokenRepository;
import com.coditas.NewJWTAssignment.util.JwtUtil;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

   public String profile(){
       return "User Profile ";
   }

   public String dashboard(){
       return "Admin Dashboard";
   }

   @Transactional
    public String logout(AuthRequest authRequest) {
       String username=jwtUtil.extractUsername(authRequest.getToken());
       RefreshToken refreshToken=refreshTokenRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Token not found in db"));
       refreshToken.setToken(null);
       return "Logout Success";
    }
}
