package com.multBancapp.apimultbanc.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_CONTA")
public class AccountEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private Integer number;

      private Integer agency;

      @ManyToOne
      private UserEntity holder;

      @ManyToOne
      private TypeAccountEntity typeAccount;

      private BigDecimal balance = BigDecimal.ZERO;

      private  BigDecimal performace;

      private Double rate;


      public void deposit(BigDecimal value) {
            balance = balance.add(value);
      }

      public void toWithdraw(BigDecimal value) {
            balance = balance.subtract(value);
      }

}
