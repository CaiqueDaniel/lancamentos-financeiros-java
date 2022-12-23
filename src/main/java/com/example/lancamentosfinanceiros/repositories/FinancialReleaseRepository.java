package com.example.lancamentosfinanceiros.repositories;

import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FinancialReleaseRepository extends JpaRepository<FinancialRelease, Long> {
    List<FinancialRelease> findAllByUser(User user, Pageable pageable);

    List<FinancialRelease> findAllByUserAndCreatedAtGreaterThanEqual(User user, LocalDateTime createdAt, Pageable pageable);

    List<FinancialRelease> findAllByUserAndCreatedAtLessThanEqual(User user, LocalDateTime createdAt, Pageable pageable);

    List<FinancialRelease> findAllByUserAndCreatedAtBetween(User user, LocalDateTime from, LocalDateTime to, Pageable pageable);

    Long countByUser(User user);

    Long countByUserAndCreatedAtGreaterThanEqual(User user, LocalDateTime createdAt);

    Long countByUserAndCreatedAtLessThanEqual(User user, LocalDateTime createdAt);

    Long countByUserAndCreatedAtBetween(User user, LocalDateTime from, LocalDateTime to);
}
