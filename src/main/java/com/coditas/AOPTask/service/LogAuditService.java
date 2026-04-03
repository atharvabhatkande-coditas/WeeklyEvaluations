package com.coditas.AOPTask.service;

import com.coditas.AOPTask.entity.LogAudit;
import com.coditas.AOPTask.repository.LogAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAuditService {
    @Autowired
    LogAuditRepository logAuditRepository;

    public List<LogAudit> getLogAudit() {
        return logAuditRepository.findAll();
    }
}
