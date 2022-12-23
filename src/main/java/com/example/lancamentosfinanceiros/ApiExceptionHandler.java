package com.example.lancamentosfinanceiros;

import com.example.lancamentosfinanceiros.exceptions.SaldoNotFoundException;
import com.example.lancamentosfinanceiros.exceptions.UsuarioExisteException;
import com.example.lancamentosfinanceiros.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            UsuarioExisteException.class
    })
    protected ResponseEntity handleConflict(RuntimeException exception, WebRequest request) {
        return this.handleExceptionInternal(
                exception,
                exception.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }

    @ExceptionHandler(value = {
            UsuarioNotFoundException.class,
            SaldoNotFoundException.class
    })
    protected ResponseEntity handleNotFound(RuntimeException exception, WebRequest request) {
        return this.handleExceptionInternal(
                exception,
                exception.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }
}
