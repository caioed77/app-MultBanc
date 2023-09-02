package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.models.dto.AccountDTO;
import com.multBancapp.apimultbanc.services.AccountService;
import com.multBancapp.apimultbanc.services.GeneratedXLSService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/conta")
public class AccountController {

    private final AccountService accountService;

    private final GeneratedXLSService generatedXLSService;

    public AccountController(AccountService accountService,GeneratedXLSService generatedXLSService) {
        this.accountService = accountService;
        this.generatedXLSService = generatedXLSService;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountDTO accountEntity) {
        return ResponseEntity.ok().body(accountService.createAccount(accountEntity));
    }

    @PutMapping(value = "/deposito")
    public ResponseEntity depositAccount(@RequestParam BigDecimal valorDeposito, @RequestParam Integer numeroConta) {
        accountService.depositAccount(valorDeposito, numeroConta);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/saque")
    public ResponseEntity withdrawAccount(@RequestParam BigDecimal valorSaque, @RequestParam Integer numeroConta) {
        accountService.withdrawAccount(valorSaque, numeroConta, 5.0);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/buscarConta")
    public ResponseEntity<AccountDTO> listAccount(@RequestParam Integer numeroConta) {
        return ResponseEntity.ok(accountService.findAccount(numeroConta));
    }

    @DeleteMapping(value = "/{codigoConta}/deletarConta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAccount(@PathVariable Long codigo) {
        accountService.deleteAccount(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/relatorio")
    public ResponseEntity<String> generetedXls(HttpServletResponse response) throws IOException {
        return ResponseEntity.of(Optional.ofNullable(generatedXLSService.generatedXls(response)));
    }

}
