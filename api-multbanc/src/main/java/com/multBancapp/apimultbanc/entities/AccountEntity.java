package com.multBancapp.apimultbanc.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

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

      @Column(name = "number_account")
      private Integer number;

      private Integer agency;

      @ManyToOne
      private UserEntity holder;

      @ManyToOne
      private TypeAccountEntity typeAccount;

      private BigDecimal balance = BigDecimal.ZERO;

      private  BigDecimal performace;

      private BigDecimal rate;


      public void deposit(BigDecimal value) {
            balance = balance.add(value);
      }

      public void toWithdraw(BigDecimal value) {
            balance = balance.subtract(value);
      }

      @Override
      public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
            Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
            if (thisEffectiveClass != oEffectiveClass) return false;
            AccountEntity that = (AccountEntity) o;
            return getId() != null && Objects.equals(getId(), that.getId());
      }

      @Override
      public final int hashCode() {
            return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
      }
}
