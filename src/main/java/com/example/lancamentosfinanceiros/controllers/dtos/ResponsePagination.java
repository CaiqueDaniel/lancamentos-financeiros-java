package com.example.lancamentosfinanceiros.controllers.dtos;

import java.util.List;
import java.util.Optional;

public class ResponsePagination<I> {
    public Meta meta;
    public List<I> itens;

    public ResponsePagination(List<I> itens, int page, int limit, Long total) {
        this.itens = itens;
        this.meta = new Meta(
                ResponsePagination.getNext(page, limit, total),
                ResponsePagination.getPrev(page),
                total
        );
    }

    private static Optional<String> getNext(int page, int limit, Long total) {
        page += 1;

        return page < total / limit ?
                Optional.of("?pagina=" + (page + 1)) :
                Optional.empty();
    }

    private static Optional<String> getPrev(int page) {
        page += 1;

        return page > 1 ?
                Optional.of("?pagina=" + (page - 1)) :
                Optional.empty();
    }
}

record Meta(Optional<String> next, Optional<String> prev, Long total) {
}