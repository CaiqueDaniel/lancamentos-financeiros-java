package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialReleaseRepository extends JpaRepository<FinancialRelease, Long>{
    List<FinancialRelease> findAllByUser(User user, Pageable pageable);

    Long countByUser(User user);

    //List<Lancamento> findAllByData(LocalDateTime data, Pageable pageable);
}
