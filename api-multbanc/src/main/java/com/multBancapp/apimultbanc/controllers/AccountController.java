package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/conta")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<AccountEntity> createAccount(
            @RequestBody AccountEntity accountEntity,
            @RequestParam String documento,
            @RequestParam String tipoConta
    ) {
        return ResponseEntity.ok().body(accountService.createAccount(accountEntity, documento, tipoConta));
    }

    @PostMapping(value = "/deposito")
    public ResponseEntity depositAccount(@RequestParam BigDecimal amount, @RequestParam String documento) {
        accountService.depositAccount(amount, documento);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/saque")
    public ResponseEntity withdrawAccount(@RequestParam BigDecimal amount, @RequestParam String documento) {
        accountService.withdrawAccount(amount, documento, 7.0);
        return ResponseEntity.ok().build();
    }

}
