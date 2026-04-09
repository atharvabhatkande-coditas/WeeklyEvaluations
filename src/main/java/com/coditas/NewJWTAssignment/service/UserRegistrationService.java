package com.coditas.NewJWTAssignment.service;

import com.coditas.NewJWTAssignment.dto.UserDto;
import com.coditas.NewJWTAssignment.entity.Users;
import com.coditas.NewJWTAssignment.exception.AlreadyExistException;
import com.coditas.NewJWTAssignment.mappers.UserMapper;
import com.coditas.NewJWTAssignment.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public String registerNewUser(UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users user=userMapper.toEntity(userDto);

        boolean existingUser=userDetailsRepository.findByUsername(user.getUsername()).isPresent();
        if(existingUser){
            throw new AlreadyExistException("Username Already Exist");
        }
        userDetailsRepository.save(user);

        return "User Registered Successfully";
    }

}
