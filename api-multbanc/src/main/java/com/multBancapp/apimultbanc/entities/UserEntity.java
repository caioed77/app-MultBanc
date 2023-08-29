package com.multBancapp.apimultbanc.entities;


import com.multBancapp.apimultbanc.entities.enums.TypePermission;
import com.multBancapp.apimultbanc.entities.enums.TypePerson;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Builder
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

      @Override
      public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
            Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
            if (thisEffectiveClass != oEffectiveClass) return false;
            UserEntity that = (UserEntity) o;
            return getId() != null && Objects.equals(getId(), that.getId());
      }

      @Override
      public final int hashCode() {
            return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
      }
}
