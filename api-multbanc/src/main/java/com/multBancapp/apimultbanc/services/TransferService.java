package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.entities.TransferEntity;
import com.multBancapp.apimultbanc.models.dto.AccountDTO;
import com.multBancapp.apimultbanc.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    private final UserService userService;

    private final AccountService accountService;

    @Autowired
    public TransferService(TransferRepository transferRepository, UserService userService, AccountService accountService) {
        this.transferRepository = transferRepository;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Transactional
    public void generatedTransfer(Integer numberSourceAccount, Integer numberDestinationAccount, BigDecimal amount) {
        var sourceAccountDTO = accountService.findAccount(numberSourceAccount);
        var sourceAccountEntity = convertDTOToEntity(sourceAccountDTO, AccountEntity.class);
        var destinationAccountDTO = accountService.findAccount(numberDestinationAccount);
        var destinationAccountEntity = convertDTOToEntity(destinationAccountDTO, AccountEntity.class);

        var userReceiver = userService.findById(sourceAccountEntity.getHolder().getId());
        var dateAt = Timestamp.valueOf(LocalDateTime.now());

        var newTransfer = new TransferEntity();
        newTransfer.setSourceAccount(sourceAccountEntity);
        newTransfer.setDestinationAccount(destinationAccountEntity);
        newTransfer.setDataTransfer(dateAt);
        newTransfer.setUserSender(userReceiver.get());
        newTransfer.setAmount(amount);
        newTransfer.setStatus("Concluido");

        transferRepository.save(newTransfer);
    }

    public <T> T convertDTOToEntity(Object dto, Class<T> entityType) {
        try {
            T entity = entityType.getDeclaredConstructor().newInstance();
            for (Method dtoMethod : dto.getClass().getMethods()) {
                if (dtoMethod.getName().startsWith("get")) {
                    String fieldName = dtoMethod.getName().substring(3);
                    Method entitySetter = entityType.getMethod("set" + fieldName, dtoMethod.getReturnType());
                    entitySetter.invoke(entity, dtoMethod.invoke(dto));
                }
            }
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }


}
