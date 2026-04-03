package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String productName);

    List<Product> findByNameIn(List<String> productNameList);
}
