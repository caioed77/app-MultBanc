package com.multBancapp.apimultbanc.models.dto;

import com.multBancapp.apimultbanc.entities.AccountEntity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransferDTO(Long sourceAccount, Long destinationAccount, BigDecimal amount, Timestamp dataTransfer, Long userId) {

}
