package com.multBancapp.apimultbanc.services;


import com.multBancapp.apimultbanc.entities.TypeAccountEntity;
import com.multBancapp.apimultbanc.repositories.TypeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TypeAccountService {


      private final TypeAccountRepository typeAccountRepository;

      @Autowired
      public TypeAccountService(TypeAccountRepository typeAccountRepository) {
            this.typeAccountRepository = typeAccountRepository;
      }

      @Transactional(readOnly = true)
      public Optional<TypeAccountEntity> findByTypeAccount(String id) {
            return typeAccountRepository.findById(id);
      }


}
