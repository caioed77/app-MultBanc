package com.multBancapp.apimultbanc.repositories.Querys;

import com.multBancapp.apimultbanc.entities.QUserEntity;
import com.multBancapp.apimultbanc.entities.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QUserDsl {

      @Autowired
      private EntityManager entityManager;

      public UserEntity findExistAccount(String email) {
            JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
            QUserEntity user = QUserEntity.userEntity;
            return queryFactory
                    .selectFrom(user)
                    .where(user.email.eq(email))
                    .fetchOne();
      }
}
