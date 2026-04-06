package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
}
