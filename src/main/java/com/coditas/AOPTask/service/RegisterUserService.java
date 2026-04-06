package com.coditas.AOPTask.service;

import com.coditas.AOPTask.DTO.ErrorResponse;
import com.coditas.AOPTask.entity.RegisterUser;
import com.coditas.AOPTask.exception.NotFoundException;
import com.coditas.AOPTask.exception.ValidationException;
import com.coditas.AOPTask.repository.RegisterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final RegisterUserRepository registerUserRepository;

    public String registerUser(RegisterUser user) {
        RegisterUser registerUser=registerUserRepository.findByUsername(user.getUsername());
        if(registerUser!=null){
            throw new ValidationException("Username Already exist");
        }
        registerUserRepository.save(user);
        return "User Registered Successfully";
    }

    public String loginUser(RegisterUser user){
        RegisterUser registerUser=registerUserRepository.findByUsername(user.getUsername());
        if(registerUser==null){
            throw new NotFoundException("No user found");
        }
        if(!user.getPassword().equals(registerUser.getPassword())){
            throw new ValidationException("Invalid Credentials");
        }
        return "User Logged in Successfully";
    }
}
