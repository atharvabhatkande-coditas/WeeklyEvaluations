package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.DTO.ErrorResponse;
import com.coditas.AOPTask.entity.LogAudit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogAuditRepository extends JpaRepository<LogAudit,Long> {

}
