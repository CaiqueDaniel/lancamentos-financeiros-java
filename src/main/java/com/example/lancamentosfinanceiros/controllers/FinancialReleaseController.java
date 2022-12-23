package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.dtos.requests.RequestFinancialReleaseDto;
import com.example.lancamentosfinanceiros.dtos.responses.ResponseFinancialReleaseDto;
import com.example.lancamentosfinanceiros.dtos.responses.ResponsePagination;
import com.example.lancamentosfinanceiros.services.FinancialReleaseService;
import com.example.lancamentosfinanceiros.services.UserService;
import com.example.lancamentosfinanceiros.controllers.utils.FinancialReleaseFilter;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.User;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/lancamento")
@PermitAll
public class FinancialReleaseController {
    @Autowired
    private FinancialReleaseService financialReleaseService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<FinancialRelease> create(@RequestBody RequestFinancialReleaseDto financialReleaseDto, Authentication auth) throws URISyntaxException {
        User user = this.userService.findOneBy(auth.getName());
        FinancialRelease financialRelease = this.financialReleaseService.create(financialReleaseDto, user);

        return ResponseEntity.created(new URI("/api/lancamento" + financialRelease.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ResponsePagination<ResponseFinancialReleaseDto>> index(
            @RequestParam(name = "pagina", defaultValue = "1") int page,
            @RequestParam(name = "de", required = false) Optional<LocalDate> fromDate,
            @RequestParam(name = "ate", required = false) Optional<LocalDate> toDate,
            Authentication auth
    ) {
        User user = this.userService.findOneBy(auth.getName());
        FinancialReleaseFilter filter = new FinancialReleaseFilter(page, fromDate, toDate);
        ResponsePagination<ResponseFinancialReleaseDto> response = this.financialReleaseService.findAllFrom(user, filter);

        return ResponseEntity.ok(response);
    }
}
