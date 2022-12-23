package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
