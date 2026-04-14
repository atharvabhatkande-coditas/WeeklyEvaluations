package com.coditas.NewJWTAssignment.repository;

import com.coditas.NewJWTAssignment.entity.Users;
import com.coditas.NewJWTAssignment.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByProviderIdAndProviderType(String providerId, ProviderType providerType);


}
