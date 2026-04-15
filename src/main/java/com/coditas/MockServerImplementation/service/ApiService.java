package com.coditas.MockServerImplementation.service;

import com.coditas.MockServerImplementation.dto.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {
    public String getProfile() {
        return "Profile";
    }
}
