package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsername(String username);
}
