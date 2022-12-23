package com.example.lancamentosfinanceiros.controllers;

import com.example.lancamentosfinanceiros.controllers.dtos.ResponseBalanceDto;
import com.example.lancamentosfinanceiros.controllers.services.BalanceService;
import com.example.lancamentosfinanceiros.controllers.services.UserService;
import com.example.lancamentosfinanceiros.models.Balance;
import com.example.lancamentosfinanceiros.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saldo")
public class BalanceController {
    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    @GetMapping
    public ResponseEntity<ResponseBalanceDto> view(Authentication auth) {
        User user = this.userService.findOne(auth.getName());
        Balance balance = this.balanceService.findOne(user);

        return ResponseEntity.ok(new ResponseBalanceDto(balance));
    }
}
