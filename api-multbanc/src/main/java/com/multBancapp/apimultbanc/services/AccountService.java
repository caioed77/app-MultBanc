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
      public void depositAccount(BigDecimal amount, Integer numberAccount) {
            var accountResult = Optional.ofNullable(accountRepository.findByNumberAccount(numberAccount))
                    .orElseThrow(() -> new BusinessRulesException("Conta não encontrada."));

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        accountResult.deposit(amount);
                  } else {
                        throw new BusinessRulesException("O valor informado não pode ser zero.");
                  }
      }

      @Transactional
      public void withdrawAccount(BigDecimal amount, Integer numberAccount, Double rate) {
            var accountResult = Optional.ofNullable(accountRepository.findByNumberAccount(numberAccount))
                    .orElseThrow(() -> new BusinessRulesException("Conta não encontrada."));

                  if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        switch (accountResult.getTypeAccount().getId()) {
                              case "C"  -> accountResult.toWithdraw(amount.subtract(BigDecimal.valueOf(rate)));
                              case "P"  -> accountResult.toWithdraw(amount);
                        }
                  } else {
                        throw new BusinessRulesException("O valor informado não pode ser zero.");
                 }
      }

      @Transactional(readOnly = true)
      public AccountDTO findAccount(Integer number){
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
