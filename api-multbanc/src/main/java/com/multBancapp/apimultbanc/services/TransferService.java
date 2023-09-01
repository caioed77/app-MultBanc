package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.entities.TransferEntity;
import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.models.dto.TransferDTO;
import com.multBancapp.apimultbanc.repositories.AccountRepository;
import com.multBancapp.apimultbanc.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    private final UserService userService;

    private final AccountRepository accountRepository;

    public TransferService(TransferRepository transferRepository, UserService userService, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void generatedTransfer(TransferDTO transferDTO) {
        var sourAccount = accountRepository.findById(transferDTO.sourceAccount())
                .orElseThrow(() -> new BusinessRulesException("Código do remetente não encontrado"));

        var destinationAccount = accountRepository.findById(transferDTO.destinationAccount())
                .orElseThrow(()-> new BusinessRulesException("Código do destinatario não encontrado"));

        var userReceiver = userService.findById(transferDTO.userId())
                .orElseThrow(() -> new BusinessRulesException("Usuario não encontrado.")) ;

        if (sourAccount.getBalance().compareTo(transferDTO.amount()) < 0) {
            throw new BusinessRulesException("Saldo insuficiente para realizar a transferência");
        } else {
            var newTransfer =  TransferEntity.builder()
                    .destinationAccount(destinationAccount)
                    .sourceAccount(sourAccount)
                    .dataTransfer(transferDTO.dataTransfer())
                    .amount(transferDTO.amount())
                    .userSender(userReceiver)
                    .status("Concluido")
                    .build();

            transferRepository.save(newTransfer);

            sourAccount.toWithdraw(transferDTO.amount());
            destinationAccount.deposit(transferDTO.amount());
        }

    }

    @Transactional(readOnly = true)
    public TransferEntity findTransfUser(UserEntity user) {
        return  transferRepository.findUserTransfer(user.getId());
    }

    @Transactional
    public void deleteTransfer(Long id) {

        var transferEntity = transferRepository.findById(id)
                .orElseThrow(() ->new BusinessRulesException("Transferência não foi encontrada."));

        if (transferEntity.getStatus().equalsIgnoreCase("Concluido")) {
            transferRepository.delete(transferEntity);
        } else {
            throw new BusinessRulesException("Esta conta possui transações pendentes. Por favor, verifique.");
        }
    }


}
