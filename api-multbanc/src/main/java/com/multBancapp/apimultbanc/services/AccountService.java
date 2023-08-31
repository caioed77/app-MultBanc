package com.multBancapp.apimultbanc.services;


import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.exceptions.ResouceNotFoundException;
import com.multBancapp.apimultbanc.models.dto.AccountDTO;
import com.multBancapp.apimultbanc.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

      private final UserService userService;

      private final AccountRepository accountRepository;

      private final TypeAccountService typeAccountService;

      private final TransferService transferService;

      public AccountService(UserService userService, AccountRepository accountRepository, TypeAccountService typeAccountService, TransferService transferService){
            this.userService = userService;
            this.accountRepository = accountRepository;
            this.typeAccountService = typeAccountService;
            this.transferService = transferService;
      }

      @Transactional
      public AccountEntity createAccount(AccountDTO account) {
            var userAccount = userService.findById(account.holder())
                    .orElseThrow(() -> new ResouceNotFoundException(account.holder()));

            var typeAcc =  typeAccountService.findByTypeAccount(account.typeAccount())
                    .orElseThrow(() -> new ResouceNotFoundException(account.typeAccount()));

            var resultEntity = AccountEntity.builder()
                    .number(account.number())
                    .agency(account.agency())
                    .holder(userAccount)
                    .typeAccount(typeAcc)
                    .balance(account.balance())
                    .performace(account.performace())
                    .rate(account.rate())
                    .build();

            accountRepository.save(resultEntity);
                  return  resultEntity;
      }

      @Transactional
      public void depositAccount(BigDecimal amount, String document) {
            var userCode = userService.findByDocument(document);
            var account = Optional.ofNullable(accountRepository.findByAccount(userCode.getId()))
                    .orElseThrow(() -> new BusinessRulesException("Conta não encontrada ou documento informado inválido."));

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        account.deposit(amount);
                  } else {
                        throw new BusinessRulesException("O valor informado não pode ser zero.");
                  }
      }

      @Transactional
      public void withdrawAccount(BigDecimal amount, String document, Double rate) {
            var userAccount = userService.findByDocument(document);
            var account = Optional.ofNullable(accountRepository.findByAccount(userAccount.getId()))
                    .orElseThrow(() -> new BusinessRulesException("Conta não encontrada ou documento informado inválido."));

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        switch (account.getTypeAccount().getId()) {
                              case "C"  -> account.toWithdraw(amount.subtract(BigDecimal.valueOf(rate)));
                              case "P"  -> account.toWithdraw(amount);
                        }
                        accountRepository.saveAndFlush(account);
                  } else {
                        throw new BusinessRulesException("O valor informado não pode ser zero.");
                 }
      }

      @Transactional(readOnly = true)
      public AccountDTO findAccount(Long number){
            var resultAccount = accountRepository.findByNumberAccount(number);
            return new AccountDTO(
                    resultAccount.getNumber(),
                    resultAccount.getAgency(),
                    resultAccount.getHolder().getId(),
                    resultAccount.getTypeAccount().getId(),
                    resultAccount.getBalance(),
                    resultAccount.getPerformace(),
                    resultAccount.getRate()
            );
      }

      @Transactional
      public void deleteAccount(Long id) {
            var accountEntity = accountRepository.findById(id)
                    .orElseThrow(() -> new BusinessRulesException("Nenhuma conta foi encontrada para esse código."));

            var transferEntity = Optional.ofNullable(transferService.findTransfUser(accountEntity.getHolder()))
                    .orElseThrow(() -> new BusinessRulesException("Transferência não encontrada."));

            transferService.deleteTransfer(transferEntity.getId());
            accountRepository.delete(accountEntity);
      }
}
