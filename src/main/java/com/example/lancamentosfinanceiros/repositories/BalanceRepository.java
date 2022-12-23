package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.Balance;
import com.example.lancamentosfinanceiros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByUsuario(User user);
}
