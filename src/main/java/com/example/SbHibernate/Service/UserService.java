package com.example.SbHibernate.Service;

import com.example.SbHibernate.Entity.User;
import com.example.SbHibernate.Exception.UserAlreadyExistsException;
import com.example.SbHibernate.Exception.UserCredentialsNotValidException;
import com.example.SbHibernate.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;
    public void registerUser(User user) {
        if(userRepo.existsByUsername(user.getUserName())){
            throw new UserAlreadyExistsException("Username Already exist");
        }else if(userRepo.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException("Email Already exist");
        }else{
            user.setPassword(encodePassword(user.getPassword()));
            userRepo.registerUser(user);
        }

    }

    public String encodePassword(String password){
        return encoder.encode(password);
    }

    public String userLogin(String userNameOrEmail,String password) {
        if(!userRepo.existsByUsername(userNameOrEmail)){
            throw new UserAlreadyExistsException("Username Does not exist");
        }
       if(encoder.matches(password,userRepo.getEncodedPassword(userNameOrEmail))){
           return "User Logged in Successfully";
       }else{
           throw new UserCredentialsNotValidException("User Credentials Not valid");
       }

    }
}
