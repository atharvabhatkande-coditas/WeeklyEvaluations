package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
}
