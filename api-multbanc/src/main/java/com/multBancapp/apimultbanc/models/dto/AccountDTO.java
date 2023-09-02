package com.multBancapp.apimultbanc.models.dto;

import com.multBancapp.apimultbanc.entities.TypeAccountEntity;
import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.models.projections.ListTransferQuantitiesProjection;
import com.multBancapp.apimultbanc.repositories.UserRepository;

import java.math.BigDecimal;

public record AccountDTO(Integer number, Integer agency, Long holder, String typeAccount, BigDecimal balance, BigDecimal performace, Double rate) {

}
