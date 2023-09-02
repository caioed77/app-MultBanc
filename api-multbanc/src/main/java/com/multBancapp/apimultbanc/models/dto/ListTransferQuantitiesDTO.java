package com.multBancapp.apimultbanc.models.dto;

import com.multBancapp.apimultbanc.models.projections.ListTransferQuantitiesProjection;
import lombok.Getter;

import java.math.BigDecimal;

public record ListTransferQuantitiesDTO(Integer numberAccount, String nameHolder, BigDecimal value) {

}
