package com.coditas.NewJWTAssignment.util;

import com.coditas.NewJWTAssignment.dto.AuthRequest;
import com.coditas.NewJWTAssignment.dto.AuthResponse;
import com.coditas.NewJWTAssignment.entity.RefreshToken;
import com.coditas.NewJWTAssignment.entity.Users;
import com.coditas.NewJWTAssignment.enums.ProviderType;
import com.coditas.NewJWTAssignment.enums.RoleType;
import com.coditas.NewJWTAssignment.exception.NotFoundException;
import com.coditas.NewJWTAssignment.repository.RefreshTokenRepository;
import com.coditas.NewJWTAssignment.repository.UserDetailsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {
    private final String SECRET = "this-is-my-secret-key-to-ppo-in-coditas-182736347@!#$";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long refreshTokenExpirationTime = 1000 * 60 * 60;
    private final long accessTokenExpirationTime = 1000 * 60 * 5;
    @Autowired
    private  UserDetailsRepository userDetailsRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public AuthResponse generateToken(Users user){
       return new AuthResponse("Login Successful",generateTokenInternal(user,accessTokenExpirationTime,"access"),generateTokenInternal(user,refreshTokenExpirationTime,"refresh"));
    }

    public String generateTokenInternal(Users user,long expirationTime,String type){
        List<String> roleTypeSet=user.getRoles().stream().map(roleType -> roleType.name()).toList();
                return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .claim("roles",roleTypeSet)
                .claim("type",type)
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

    public String extractType(String token){
        return validateAndExtractClaims(token).get("type",String.class);
    }

    public boolean validateToken(String username, String token, UserDetails userDetails){

        return username.equals(userDetails.getUsername()) && !isExpired(token);

    }

    private boolean isExpired(String token){
        return validateAndExtractClaims(token).getExpiration().before(new Date());
    }

    public ProviderType findProviderType(String registrationId) {
        return switch (registrationId.toLowerCase()){
            case "google"->ProviderType.GOOGLE;
            case "facebook"->ProviderType.FACEBOOK;
            case "github"->ProviderType.GITHUB;
            default -> throw new ValidationException("Invalid Provider type");
        };
    }

    public String findProviderId(OAuth2User oAuth2User, String registrationId) {
        String providerId=switch(registrationId.toLowerCase()){
          case "google"->oAuth2User.getAttribute("sub");
          case "github"->oAuth2User.getAttribute("id").toString();
            default -> {
                log.error("Invalid  Oauth Provider:{}",registrationId);
                throw  new ValidationException("Invalid Oauth provider");
            }

        };

        if(providerId==null || providerId.isBlank()){
            log.error("Failed to Extract provider id:{}",registrationId);
            throw new ValidationException("Failed to Extract provider id");
        }

        return providerId;
    }

    public String determineUsername(OAuth2User oAuth2User, String registrationId, String providerId) {
        String email=oAuth2User.getAttribute("email");
        if(email!=null && !email.isBlank()){
            return email;
        }

        return switch (registrationId.toLowerCase()){
            case "google"->oAuth2User.getAttribute("sub");
            case "github"->oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }

    public String generateAccessToken(AuthRequest authRequest) {
        String tokenType=extractType(authRequest.getToken());
        if(tokenType.equals("access")){

            throw new ValidationException("Invalid Token type");
        }
        if(isExpired(authRequest.getToken())){
            throw new ValidationException("Refresh Token Expired");
        }
        RefreshToken refreshToken=refreshTokenRepository.findByToken(authRequest.getToken()).orElseThrow(()->new NotFoundException("Please Login again"));
        String username=extractUsername(authRequest.getToken());
        Users user=userDetailsRepository.findByUsername(username).orElseThrow(()->new NotFoundException("User not found"));
        return generateTokenInternal(user,accessTokenExpirationTime,"access");
    }
}
