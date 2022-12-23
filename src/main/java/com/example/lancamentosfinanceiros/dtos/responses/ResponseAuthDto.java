package com.example.lancamentosfinanceiros.dtos.responses;

public class ResponseAuthDto {
    public String token;
    public Long expireAt;

    public ResponseAuthDto(String token, Long expireAt) {
        this.token = "Bearer " + token;
        this.expireAt = expireAt;
    }
}
