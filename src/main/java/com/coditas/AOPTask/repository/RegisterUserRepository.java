package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterUserRepository extends JpaRepository<RegisterUser,Long> {
    RegisterUser findByUsername(String username);
}
