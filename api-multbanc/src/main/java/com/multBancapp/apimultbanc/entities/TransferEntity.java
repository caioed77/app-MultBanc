package com.multBancapp.apimultbanc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;


@Builder
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

      @Override
      public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
            Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
            if (thisEffectiveClass != oEffectiveClass) return false;
            TransferEntity that = (TransferEntity) o;
            return getId() != null && Objects.equals(getId(), that.getId());
      }

      @Override
      public final int hashCode() {
            return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
      }
}
