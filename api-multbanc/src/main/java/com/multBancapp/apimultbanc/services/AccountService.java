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
            var people = userService.findByDocument(document);
            var typeAccount =  typeAccountService.findByTypeAccount(tipoConta).get();
            var poupanca = new AccountEntity(account.getId(), account.getNumber(), account.getAgency(), people, typeAccount, account.getBalance(), account.getPerformace(), account.getRate());
            return accountRepository.save(poupanca);
      }


      @Transactional
      public void depositAccount(BigDecimal amount, String document) {
            var peopleKey = userService.findByDocument(document);
            var account = Optional.ofNullable(accountRepository.findByAccount(peopleKey));

            if (account.isPresent()) {

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        account.get().deposit(amount);
                  } else {
                        throw new BusinessRulesException("O Valor informado não pode ser zero");
                  }

            } else {
                  throw new BusinessRulesException("Conta não encontrada ou documento informado invalido");
            }

      }

      @Transactional
      public void withdrawAccount(BigDecimal amount, String document, Double rate) {
            var people = userService.findByDocument(document);
            var account = Optional.ofNullable(accountRepository.findByAccount(people));

            if (account.isPresent()) {

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {

                        if (account.get().getTypeAccount().getId().equals("C")) {
                              account.get().toWithdraw(amount.subtract(BigDecimal.valueOf(rate)));
                        }

                        if (account.get().getTypeAccount().getId().equals("P")) {
                              account.get().toWithdraw(amount);
                        }

                        accountRepository.saveAndFlush(account.get());
                  } else {
                        throw new BusinessRulesException("O Valor informado não pode ser zero");
                  }

            } else {
                  throw new BusinessRulesException("Conta não encontrada ou documento informado invalido");
            }
      }

      @Transactional(readOnly = true)
      public AccountDTO findAccount(Integer number){
            var resultAccount = accountRepository.findByNumberAccount(number);
            return new AccountDTO(resultAccount.getNumber(), resultAccount.getAgency(), resultAccount.getHolder(), resultAccount.getTypeAccount(), resultAccount.getBalance(), resultAccount.getPerformace(), resultAccount.getRate());
      }
}
