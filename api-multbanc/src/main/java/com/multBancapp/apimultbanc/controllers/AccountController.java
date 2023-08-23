package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "multBanc/conta")
public class AccountController {

      private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
          this.accountService = accountService;
    }


    @PostMapping(value = "/cadastrarContaPoupanca")
    public ResponseEntity<AccountEntity> createSavingsAccount(@RequestBody AccountEntity accountEntity, @RequestParam String documento) {
            return  ResponseEntity.ok().body(accountService.createConta(accountEntity, documento, "P"));
    }

   @PostMapping(value = "/cadastrarContaCorrente")
   public ResponseEntity<AccountEntity> createCheckingAccount(@RequestBody AccountEntity accountEntity, @RequestParam String documento) {
         return  ResponseEntity.ok().body(accountService.createConta(accountEntity, documento, "C"));
   }




}
