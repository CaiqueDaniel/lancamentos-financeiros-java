package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.Saldo;
import com.example.lancamentosfinanceiros.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
    Optional<Saldo> findByUsuario(Usuario usuario);
}
