package com.multBancapp.apimultbanc.repositories;

import com.multBancapp.apimultbanc.entities.TransferEntity;
import com.multBancapp.apimultbanc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

      @Query(value = "Select t from TransferEntity t where t.userSender = :user")
      TransferEntity findUserTransfer(Long user);

}
