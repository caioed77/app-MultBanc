package com.multBancapp.apimultbanc.entities;


import com.multBancapp.apimultbanc.entities.enums.TypePermission;
import com.multBancapp.apimultbanc.entities.enums.TypePerson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_USUARIOS")
public class UserEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String email;

      private String password;

      private String document;

      private TypePerson typePerson = TypePerson.FISICA;

      private String blocked;

      private TypePermission typePermission = TypePermission.GUEST;

      private String imgUser;

}
