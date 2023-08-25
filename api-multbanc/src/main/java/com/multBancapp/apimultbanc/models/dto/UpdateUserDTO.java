package com.multBancapp.apimultbanc.models.dto;

import com.multBancapp.apimultbanc.entities.enums.TypePerson;

public record UpdateUserDTO(String setDocument, String imgUser, TypePerson typePerson) {

}


