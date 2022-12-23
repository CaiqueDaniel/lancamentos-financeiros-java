package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.Lancamento;
import com.example.lancamentosfinanceiros.models.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    List<Lancamento> findAllByUsuario(Usuario usuario, Pageable pageable);

    Long countByUsuario(Usuario usuario);

    //List<Lancamento> findAllByData(LocalDateTime data, Pageable pageable);
}
