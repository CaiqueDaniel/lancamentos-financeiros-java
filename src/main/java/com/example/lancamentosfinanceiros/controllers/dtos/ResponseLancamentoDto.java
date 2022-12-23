package com.example.lancamentosfinanceiros.controllers.dtos;

import com.example.lancamentosfinanceiros.models.Lancamento;

public class ResponseLancamentoDto extends RequestLancamentoDto {
    public ResponseLancamentoDto(Lancamento lancamento) {
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.data = lancamento.getData();
    }
}
