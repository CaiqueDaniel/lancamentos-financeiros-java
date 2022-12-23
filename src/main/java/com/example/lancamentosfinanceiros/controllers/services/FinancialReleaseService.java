package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestFinancialReleaseDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponseFinancialReleaseDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponsePagination;
import com.example.lancamentosfinanceiros.controllers.utils.FinancialReleaseFilter;
import com.example.lancamentosfinanceiros.exceptions.InternalServerException;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.Balance;
import com.example.lancamentosfinanceiros.models.User;
import com.example.lancamentosfinanceiros.repositories.FinancialReleaseRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialReleaseService {
    @Autowired
    private FinancialReleaseRepository repository;

    @Autowired
    private BalanceService balanceService;

    private final static int LIMIT = 30;

    public FinancialRelease create(RequestFinancialReleaseDto financialReleaseDto, User user) throws HttpServerErrorException.InternalServerError {
        FinancialRelease financialRelease = new FinancialRelease(financialReleaseDto, user);
        Balance balance = this.balanceService.findOneBy(user);
        BigDecimal previousBalanceValue = balance.getValue();

        try {
            balance.setValue(balance.getValue().add(financialRelease.getValue()));
            this.balanceService.update(balance);

            return this.repository.saveAndFlush(financialRelease);
        } catch (HibernateException exception) {
            balance.setValue(previousBalanceValue);
            this.balanceService.update(balance);

            throw new InternalServerException(exception.getLocalizedMessage());
        }
    }

    public ResponsePagination<ResponseFinancialReleaseDto> findAllFrom(User user, FinancialReleaseFilter filter) {
        int page = filter.page() > 0 ? filter.page() : 0;

        Long total;
        Pageable pageable = PageRequest.of(page - 1, FinancialReleaseService.LIMIT);
        List<FinancialRelease> financialReleases = new ArrayList<>();

        if (filter.fromDate().isPresent() && filter.toDate().isPresent()) {
            LocalDateTime from = filter.fromDate().get().atStartOfDay(), to = filter.toDate().get().atStartOfDay();

            financialReleases = this.repository.findAllByUserAndCreatedAtBetween(user, from, to, pageable);
            total = this.repository.countByUserAndCreatedAtBetween(user, from, to);
        } else if (filter.fromDate().isPresent()) {
            LocalDateTime from = filter.fromDate().get().atStartOfDay();

            financialReleases = this.repository.findAllByUserAndCreatedAtGreaterThanEqual(user, from, pageable);
            total = this.repository.countByUserAndCreatedAtGreaterThanEqual(user, from);
        } else if (filter.toDate().isPresent()) {
            LocalDateTime to = filter.toDate().get().atStartOfDay();

            financialReleases = this.repository.findAllByUserAndCreatedAtLessThanEqual(user, to, pageable);
            total = this.repository.countByUserAndCreatedAtLessThanEqual(user, to);
        } else {
            financialReleases = this.repository.findAllByUser(user, pageable);
            total = this.repository.countByUser(user);
        }

        List<ResponseFinancialReleaseDto> financialReleaseDtos = financialReleases.stream()
                .map((financialRelease) -> new ResponseFinancialReleaseDto(financialRelease))
                .collect(Collectors.toList());

        return new ResponsePagination<>(financialReleaseDtos, page, FinancialReleaseService.LIMIT, total);
    }
}
