package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.config.SecurityConfig;
import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.models.dto.UserDTO;
import com.multBancapp.apimultbanc.repositories.UserRepository;
import com.multBancapp.apimultbanc.services.utils.ValidateCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

      private final UserRepository userRepository;

      @Autowired
      private SecurityConfig passwordEncoder;

      @Autowired
      public UserService(UserRepository userRepository){
            this.userRepository = userRepository;
      }

      @Transactional(readOnly = true)
      public Optional<UserEntity> findById(Long id) {
            return userRepository.findById(id);
      }

      public UserEntity findByDocument(String document) {
            Optional<UserEntity> result =  Optional.ofNullable(userRepository.findDocument(document));
            return result.orElseThrow(() -> new BusinessRulesException("Documento invalido"));
      }

      @Transactional
      public UserEntity createUser(UserEntity user) {
            var validadeCPF = new ValidateCPF();

            if (validadeCPF.validade(user)) {
                  var encryptedPassword = passwordEncoder.passwordEncoder().encode(user.getPassword());
                  user.setPassword(encryptedPassword);

                  return userRepository.save(user);
            } else {
                  throw new BusinessRulesException("CPF Invalido");
            }
      }

      @Transactional(readOnly = true)
      public UserDTO findUser(String document) {
            var resultDocument = userRepository.findDocument(document);
            return new UserDTO(
                    resultDocument.getEmail(),
                    resultDocument.getPassword(),
                    resultDocument.getDocument(),
                    resultDocument.getTypePerson()
            );
      }


}
