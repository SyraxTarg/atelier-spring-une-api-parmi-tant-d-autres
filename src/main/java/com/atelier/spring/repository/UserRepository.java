package com.atelier.spring.repository;

import com.atelier.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByClaimToken(String token);
}