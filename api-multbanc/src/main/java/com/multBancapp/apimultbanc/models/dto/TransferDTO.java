package com.multBancapp.apimultbanc.models.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransferDTO(Long sourceAccount, Long destinationAccount, BigDecimal amount, Timestamp dataTransfer, Long userId) {

}
