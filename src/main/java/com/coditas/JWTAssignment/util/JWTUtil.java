package com.coditas.JWTAssignment.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final long expirationTime=1000*60*10;
    private final String SECRET="thi-is-my-secret-key-128828!*@*@";
    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String username) {
        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
        System.out.println("Token : "+token);
        return token;
    }
}
