package com.example.lancamentosfinanceiros.controllers.services;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestLancamentoDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponseLancamentoDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponsePagination;
import com.example.lancamentosfinanceiros.exceptions.InternalServerException;
import com.example.lancamentosfinanceiros.models.Lancamento;
import com.example.lancamentosfinanceiros.models.Saldo;
import com.example.lancamentosfinanceiros.models.Usuario;
import com.example.lancamentosfinanceiros.repositories.LancamentoRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private SaldoService saldoService;

    private final static int LIMIT = 30;

    public Lancamento create(RequestLancamentoDto lancamentoDto, Usuario usuario) throws HttpServerErrorException.InternalServerError {
        Lancamento lancamento = new Lancamento(lancamentoDto, usuario);
        Saldo saldo = this.saldoService.findOne(usuario);
        BigDecimal valorAnterior = saldo.getValor();

        try {
            saldo.setValor(saldo.getValor().add(lancamento.getValor()));
            this.saldoService.update(saldo);

            return this.repository.saveAndFlush(lancamento);
        } catch (HibernateException exception) {
            saldo.setValor(valorAnterior);
            this.saldoService.update(saldo);

            throw new InternalServerException(exception.getLocalizedMessage());
        }
    }

    public ResponsePagination<ResponseLancamentoDto> findAllFrom(Usuario usuario, int page) {
        page = page > 0 ? page - 1 : 0;

        Long total = this.repository.countByUsuario(usuario);
        List<ResponseLancamentoDto> lancamentoDtos = this.repository.findAllByUsuario(usuario, PageRequest.of(page, LancamentoService.LIMIT))
                .stream().map((lancamento) -> new ResponseLancamentoDto(lancamento)).collect(Collectors.toList());

        return new ResponsePagination<>(lancamentoDtos, page, LancamentoService.LIMIT, total);
    }
}
