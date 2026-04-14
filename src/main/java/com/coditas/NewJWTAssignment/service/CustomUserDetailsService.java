package com.coditas.NewJWTAssignment.service;

import com.coditas.NewJWTAssignment.exception.NotFoundException;
import com.coditas.NewJWTAssignment.repository.UserDetailsRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDetails userDetails= userDetailsRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username not found"));
        return userDetails;
    }
}
