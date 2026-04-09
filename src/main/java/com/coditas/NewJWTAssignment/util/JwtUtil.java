package com.coditas.NewJWTAssignment.util;

import com.coditas.NewJWTAssignment.dto.AuthResponse;
import com.coditas.NewJWTAssignment.entity.Users;
import com.coditas.NewJWTAssignment.exception.NotFoundException;
import com.coditas.NewJWTAssignment.repository.UserDetailsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private final String SECRET = "this-is-my-secret-key-to-ppo-in-coditas-182736347@!#$";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long expirationTime = 1000 * 60 * 60;

    public AuthResponse generateToken(String username, String password){
        Users user=userDetailsRepository.findByUsername(username).orElseThrow(()->new NotFoundException("User not found"));
       return new AuthResponse("Login Successful",Jwts.builder()
               .setSubject(username)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
               .signWith(key, SignatureAlgorithm.HS256)
               .claim("role",user.getRole())
               .compact());
    }
    private Claims validateAndExtractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return validateAndExtractClaims(token).getSubject();
    }

    public boolean validateToken(String username, String token, UserDetails userDetails){

        return username.equals(userDetails.getUsername()) && !isExpired(token);

    }

    private boolean isExpired(String token){
        return validateAndExtractClaims(token).getExpiration().before(new Date());
    }
}
