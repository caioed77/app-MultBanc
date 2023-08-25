package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.entities.TransferEntity;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.repositories.AccountRepository;
import com.multBancapp.apimultbanc.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    private final UserService userService;

    private final AccountRepository accountRepository;

    @Autowired
    public TransferService(TransferRepository transferRepository, UserService userService, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void generatedTransfer(Integer numberSourceAccount, Integer numberDestinationAccount, BigDecimal amount) {
        var sourceAccount = accountRepository.findByNumberAccount(numberSourceAccount);
        var destinationAccount = accountRepository.findByNumberAccount(numberDestinationAccount);

        var userReceiver = userService.findById(sourceAccount.getHolder().getId());
        var dateAt = Timestamp.valueOf(LocalDateTime.now());

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new BusinessRulesException("Saldo insuficiente para realizar a transferência");
        } else {
            var newTransfer = new TransferEntity();
            newTransfer.setSourceAccount(sourceAccount);
            newTransfer.setDestinationAccount(destinationAccount);
            newTransfer.setDataTransfer(dateAt);
            newTransfer.setUserSender(userReceiver.get());
            newTransfer.setAmount(amount);
            newTransfer.setStatus("Concluido");
            transferRepository.save(newTransfer);

            sourceAccount.toWithdraw(amount);
            destinationAccount.deposit(amount);
        }

    }

}
