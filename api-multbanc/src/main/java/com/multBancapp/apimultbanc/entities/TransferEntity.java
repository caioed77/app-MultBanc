package com.multBancapp.apimultbanc.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(of = "id")
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

      private String receiver;

      private String sender;

      private BigDecimal amount;

      private LocalDate dataTransfer;

      @ManyToOne
      private UserEntity user;
}
