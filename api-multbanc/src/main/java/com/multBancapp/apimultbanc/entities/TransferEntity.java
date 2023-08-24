package com.multBancapp.apimultbanc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TB_TRANSFERENCIA")
public class TransferEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne
      private AccountEntity sourceAccount;

      @ManyToOne
      private AccountEntity destinationAccount;

      private BigDecimal amount;

      private Timestamp dataTransfer;

      @ManyToOne
      private UserEntity userSender;

      private String status;
}
