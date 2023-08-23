package com.multBancapp.apimultbanc.models;

import com.multBancapp.apimultbanc.entities.enums.TypePerson;

public record UserDTO(String email, String password, String document, TypePerson typePerson) {

}
