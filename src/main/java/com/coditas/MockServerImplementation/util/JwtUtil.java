package com.coditas.MockServerImplementation.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final String SECRET = "this-is-my-secret-key-to-ppo-in-coditas-182736347@!#$";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long expirationTime = 1000 * 60 * 5;


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
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
