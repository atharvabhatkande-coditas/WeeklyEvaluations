package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
