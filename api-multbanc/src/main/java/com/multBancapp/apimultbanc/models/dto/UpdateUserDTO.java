package com.multBancapp.apimultbanc.models.dto;

import com.multBancapp.apimultbanc.entities.enums.TypePerson;

public record UpdateUserDTO(String document, String imgUser, TypePerson typePerson) {

}


