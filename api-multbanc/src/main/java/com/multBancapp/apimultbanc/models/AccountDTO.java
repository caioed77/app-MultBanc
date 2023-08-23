package com.multBancapp.apimultbanc.models;

import com.multBancapp.apimultbanc.entities.TypeAccountEntity;
import com.multBancapp.apimultbanc.entities.UserEntity;

import java.math.BigDecimal;

public record AccountDTO(Long id, Integer number, Integer agency, UserEntity holder, TypeAccountEntity typeAccount, BigDecimal balance, BigDecimal performace, Double rate) {

}
