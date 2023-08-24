package com.multBancapp.apimultbanc.repositories;

import com.multBancapp.apimultbanc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

      @Query(value = "Select u from UserEntity u where u.document = :doc")
      UserEntity findDocument(String doc);

      Optional<UserEntity> findByEmail(String email);

}

