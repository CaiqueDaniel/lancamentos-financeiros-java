package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestFinancialReleaseDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponseFinancialReleaseDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponsePagination;
import com.example.lancamentosfinanceiros.exceptions.InternalServerException;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.Balance;
import com.example.lancamentosfinanceiros.models.User;
import com.example.lancamentosfinanceiros.repositories.FinancialReleaseRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialReleaseService {
    @Autowired
    private FinancialReleaseRepository repository;

    @Autowired
    private BalanceService balanceService;

    private final static int LIMIT = 30;

    public FinancialRelease create(RequestFinancialReleaseDto lancamentoDto, User user) throws HttpServerErrorException.InternalServerError {
        FinancialRelease financialRelease = new FinancialRelease(lancamentoDto, user);
        Balance balance = this.balanceService.findOne(user);
        BigDecimal valorAnterior = balance.getValor();

        try {
            balance.setValor(balance.getValor().add(financialRelease.getValor()));
            this.balanceService.update(balance);

            return this.repository.saveAndFlush(financialRelease);
        } catch (HibernateException exception) {
            balance.setValor(valorAnterior);
            this.balanceService.update(balance);

            throw new InternalServerException(exception.getLocalizedMessage());
        }
    }

    public ResponsePagination<ResponseFinancialReleaseDto> findAllFrom(User user, int page) {
        page = page > 0 ? page - 1 : 0;

        Long total = this.repository.countByUsuario(user);
        List<ResponseFinancialReleaseDto> lancamentoDtos = this.repository.findAllByUsuario(user, PageRequest.of(page, FinancialReleaseService.LIMIT))
                .stream().map((financialRelease) -> new ResponseFinancialReleaseDto(financialRelease)).collect(Collectors.toList());

        return new ResponsePagination<>(lancamentoDtos, page, FinancialReleaseService.LIMIT, total);
    }
}
