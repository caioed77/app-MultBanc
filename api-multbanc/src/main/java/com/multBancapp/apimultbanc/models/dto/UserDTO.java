package com.multBancapp.apimultbanc.models.dto;

import com.multBancapp.apimultbanc.entities.enums.TypePerson;

public record UserDTO(String email, String password, String document, TypePerson typePerson, String imgUser) {

}
