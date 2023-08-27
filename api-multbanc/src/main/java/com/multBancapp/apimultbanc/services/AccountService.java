package com.multBancapp.apimultbanc.services;


import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.models.dto.AccountDTO;
import com.multBancapp.apimultbanc.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

      private final UserService userService;

      private final AccountRepository accountRepository;

      private final TypeAccountService typeAccountService;


      @Autowired
      public AccountService(UserService userService, AccountRepository accountRepository, TypeAccountService typeAccountService){
            this.userService = userService;
            this.accountRepository = accountRepository;
            this.typeAccountService = typeAccountService;
      }

      @Transactional
      public AccountEntity createAccount(AccountEntity account, String document, String tipoConta) {
            var userAccount = userService.findByDocument(document);
            var typeAccount =  typeAccountService.findByTypeAccount(tipoConta).get();
            var newAccount = new AccountEntity(account.getId(), account.getNumber(), account.getAgency(), userAccount, typeAccount, account.getBalance(), account.getPerformace(), account.getRate());
            return accountRepository.save(newAccount);
      }


      @Transactional
      public void depositAccount(BigDecimal amount, String document) {
            var userCode = userService.findByDocument(document);
            var account = Optional.ofNullable(accountRepository.findByAccount(userCode))
                    .orElseThrow(() -> new BusinessRulesException("Conta não encontrada ou documento informado invalido"));

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        account.deposit(amount);
                  } else {
                        throw new BusinessRulesException("O Valor informado não pode ser zero");
                  }
      }

      @Transactional
      public void withdrawAccount(BigDecimal amount, String document, Double rate) {
            var userAccount = userService.findByDocument(document);
            var account = Optional.ofNullable(accountRepository.findByAccount(userAccount))
                    .orElseThrow(() -> new BusinessRulesException("Conta não encontrada ou documento informado invalido"));

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {

                        switch (account.getTypeAccount().getId()) {
                              case "C"  -> account.toWithdraw(amount.subtract(BigDecimal.valueOf(rate)));
                              case "P"  ->  account.toWithdraw(amount);
                        }

                        accountRepository.saveAndFlush(account);
                  } else {
                        throw new BusinessRulesException("O Valor informado não pode ser zero");
                  }
      }

      @Transactional(readOnly = true)
      public AccountDTO findAccount(Integer number){
            var resultAccount = accountRepository.findByNumberAccount(number);
            return new AccountDTO(resultAccount.getNumber(), resultAccount.getAgency(), resultAccount.getHolder(), resultAccount.getTypeAccount(), resultAccount.getBalance(), resultAccount.getPerformace(), resultAccount.getRate());
      }
}
