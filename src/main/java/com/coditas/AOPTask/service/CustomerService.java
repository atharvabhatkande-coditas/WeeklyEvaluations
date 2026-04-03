package com.coditas.AOPTask.service;

import com.coditas.AOPTask.entity.Customer;
import com.coditas.AOPTask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

}
