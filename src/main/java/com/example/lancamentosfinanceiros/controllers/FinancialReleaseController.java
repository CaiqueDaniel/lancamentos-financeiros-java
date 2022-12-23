package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.RequestFinancialReleaseDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponseFinancialReleaseDto;
import com.example.lancamentosfinanceiros.controllers.dtos.ResponsePagination;
import com.example.lancamentosfinanceiros.controllers.services.FinancialReleaseService;
import com.example.lancamentosfinanceiros.controllers.services.UserService;
import com.example.lancamentosfinanceiros.models.FinancialRelease;
import com.example.lancamentosfinanceiros.models.User;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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

        return ResponseEntity.created(new URI("/api/lancamento/" + financialRelease.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ResponsePagination<ResponseFinancialReleaseDto>> index(@RequestParam(name = "pagina", defaultValue = "1") int page, Authentication auth) {
        User user = this.userService.findOneBy(auth.getName());
        ResponsePagination<ResponseFinancialReleaseDto> response= this.financialReleaseService.findAllFrom(user, page);

        return ResponseEntity.ok(response);
    }
}
