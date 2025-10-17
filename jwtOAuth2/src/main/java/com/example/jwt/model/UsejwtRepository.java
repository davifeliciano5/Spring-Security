package com.example.jwt.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsejwtRepository extends JpaRepository<Userjwt, Long> {
    Optional<Userjwt> findByUsername(String username);
}
