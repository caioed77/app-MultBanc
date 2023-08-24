package com.multBancapp.apimultbanc.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
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

      public AccountEntity(Integer number, Integer agency, UserEntity holder, TypeAccountEntity typeAccountEntity, BigDecimal balance, BigDecimal performace, Double rate) {
            this.number = number;
            this.agency = agency;
            this.holder = holder;
            this.typeAccount = typeAccountEntity;
            this.balance = balance;
            this.performace = performace;
            this.rate = rate;
      }

      public void deposit(BigDecimal value) {
            balance = balance.add(value);
      }

      public void toWithdraw(BigDecimal value) {
            balance = balance.subtract(value);
      }

}
