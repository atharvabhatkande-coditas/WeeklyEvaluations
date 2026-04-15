package com.coditas.MockServerImplementation.service;

import com.coditas.MockServerImplementation.dto.ErrorResponse;
import com.coditas.MockServerImplementation.dto.RequestDto;
import com.coditas.MockServerImplementation.dto.ResponseDto;
import com.coditas.MockServerImplementation.entity.Users;
import com.coditas.MockServerImplementation.exception.AlreadyExistException;
import com.coditas.MockServerImplementation.exception.NotFoundException;
import com.coditas.MockServerImplementation.exception.ValidationException;
import com.coditas.MockServerImplementation.repository.UserRepository;
import com.coditas.MockServerImplementation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ResponseDto loginUser(Users user) {
        Users existingUser=userRepository.findByUsername(user.getUsername()).orElseThrow(()->new NotFoundException("Username not registered"));
        boolean isValid=passwordEncoder().matches(user.getPassword(),existingUser.getPassword());
        if(!isValid){
            throw new ValidationException("Bad Credentials");
        }

        String authToken= existingUser.getPassword();
        return new ResponseDto(authToken);
    }

    public ResponseDto generateToken(RequestDto requestDto) {
        Users user=userRepository.findByPassword(requestDto.getAuthToken()).orElseThrow(()->new ValidationException("Auth Token Invalid"));
        String jwtToken=jwtUtil.generateToken(user.getUsername());
        return new ResponseDto(jwtToken);
    }

    public String getProfile() {
        return "Profile";
    }

    public String registerUser(Users user) {
        Users existingUser=userRepository.findByUsername(user.getUsername()).orElse(null);
        if(existingUser!=null){
            throw new AlreadyExistException("Username Already Exists");
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "Registration successful";

    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
