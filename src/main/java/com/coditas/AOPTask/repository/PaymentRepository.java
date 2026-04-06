package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
