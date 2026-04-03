package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
