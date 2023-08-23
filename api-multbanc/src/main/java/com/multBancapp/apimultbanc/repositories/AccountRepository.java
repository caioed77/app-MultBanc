package com.multBancapp.apimultbanc.repositories;

import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

      @Query(value = "SELECT a FROM AccountEntity a WHERE a.holder = :holder")
      AccountEntity findByAccount(UserEntity holder);
}
