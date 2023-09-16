package com.multBancapp.apimultbanc.repositories.Querys;

import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.entities.QAccountEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QAccountDsl {

      @Autowired
      private EntityManager entityManager;

      public AccountEntity findExistAccount(Integer numAccount) {
            JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
            QAccountEntity account = QAccountEntity.accountEntity;
            return queryFactory
                    .selectFrom(account)
                    .where(account.number.eq(numAccount))
                    .fetchOne();
      }

}
