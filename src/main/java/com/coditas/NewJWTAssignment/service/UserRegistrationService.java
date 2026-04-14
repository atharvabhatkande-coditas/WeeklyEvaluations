package com.coditas.NewJWTAssignment.service;

import com.coditas.NewJWTAssignment.dto.ApplicationResponse;
import com.coditas.NewJWTAssignment.dto.AuthResponse;
import com.coditas.NewJWTAssignment.dto.UserDto;
import com.coditas.NewJWTAssignment.entity.RefreshToken;
import com.coditas.NewJWTAssignment.entity.Users;
import com.coditas.NewJWTAssignment.enums.ProviderType;
import com.coditas.NewJWTAssignment.enums.RoleType;
import com.coditas.NewJWTAssignment.exception.AlreadyExistException;
import com.coditas.NewJWTAssignment.exception.NotFoundException;
import com.coditas.NewJWTAssignment.mappers.UserMapper;
import com.coditas.NewJWTAssignment.repository.RefreshTokenRepository;
import com.coditas.NewJWTAssignment.repository.UserDetailsRepository;
import com.coditas.NewJWTAssignment.util.JwtUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final  UserDetailsRepository userDetailsRepository;
    private final UserMapper userMapper;
    private  final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;



    public Users registerNewUser(UserDto userDto,ProviderType providerType,String providerId){

        Users user=userMapper.toEntity(userDto);

        boolean existingUser=userDetailsRepository.findByUsername(user.getUsername()).isPresent();
        if(existingUser){
            throw new AlreadyExistException("Username Already Exist");
        }
        user.setProviderId(providerId);
        user.setProviderType(providerType);
        userDetailsRepository.save(user);

        return user;
    }


    public ResponseEntity<ApplicationResponse<AuthResponse>> handleOauthResponse(OAuth2User oAuth2User, String registrationId) {
        ProviderType providerType=jwtUtil.findProviderType(registrationId);
        String providerId=jwtUtil.findProviderId(oAuth2User,registrationId);

        Users user=userDetailsRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);

        String email=oAuth2User.getAttribute("email");

        Users emailUser=userDetailsRepository.findByUsername(email).orElse(null);

        if(user==null && emailUser==null){

        String username=jwtUtil.determineUsername(oAuth2User,registrationId,providerId);
        user =registerNewUser(new UserDto(username,null,true, Set.of(RoleType.ROLE_USER)),providerType,providerId);


        }else if(user!=null){
                if( email!=null && !email.isBlank() && !email.equals(user.getUsername())){
                    user.setUsername(email);
                    userDetailsRepository.save(user);
                }
        }else{
            throw new ValidationException("Email already registered with provider:"+providerType);
        }

        AuthResponse authResponse=jwtUtil.generateToken(user);
        RefreshToken existingRefreshToken =refreshTokenRepository.findByUsername(user.getUsername()).orElse(null);
     if(existingRefreshToken!=null){
         existingRefreshToken.setToken(authResponse.getRefreshToken());
         refreshTokenRepository.save(existingRefreshToken);
     }
     else{
         RefreshToken refreshToken=new RefreshToken(null,user.getUsername(), authResponse.getRefreshToken());
         refreshToken.setUser(user);
         refreshTokenRepository.save(refreshToken);
     }
        ApplicationResponse<AuthResponse>applicationResponse=new ApplicationResponse<>(authResponse);
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

}
