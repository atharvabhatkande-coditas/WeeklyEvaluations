package com.coditas.AOPTask.controller;

import com.coditas.AOPTask.DTO.ApplicationResponse;
import com.coditas.AOPTask.entity.Customer;
import com.coditas.AOPTask.entity.LogAudit;
import com.coditas.AOPTask.repository.LogAuditRepository;
import com.coditas.AOPTask.service.CustomerService;
import com.coditas.AOPTask.service.LogAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final  LogAuditService logAuditService;

    @GetMapping("/getLogs")
    public ResponseEntity<ApplicationResponse<List<LogAudit>>> auditLogs(){
        ApplicationResponse<List<LogAudit>>applicationResponse=new ApplicationResponse<>(logAuditService.getLogAudit());
        return  new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

}
