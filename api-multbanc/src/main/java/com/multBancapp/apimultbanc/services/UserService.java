package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.config.SecurityConfig;
import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.models.dto.UserDTO;
import com.multBancapp.apimultbanc.repositories.UserRepository;
import com.multBancapp.apimultbanc.services.utils.ValidateCPF;
import com.multBancapp.apimultbanc.services.utils.ValidateEmail;
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
                    resultDocument.getTypePerson(),
                    resultDocument.getImgUser()
            );
      }

      public boolean isValid(String email, String senha) {
            var userResult = userRepository.findByEmail(email);
            if (userResult.isEmpty())
                  return false;

            var comparePassword = passwordEncoder.passwordEncoder().matches(senha, userResult.get().getPassword());

            if (comparePassword && !userResult.get().getBlocked().equals("T"))
                  return true;

            throw  new BusinessRulesException("Usuário não encontrado");
      }

      @Transactional
      public void  registerLogin(UserDTO userDTO) {
            var validateEmail = new ValidateEmail();

            if (validateEmail.validate(userDTO.email())) {
                  var newUser = new UserEntity();
                  var encryptedPassword = passwordEncoder.passwordEncoder().encode(userDTO.password());
                  newUser.setEmail(userDTO.email());
                  newUser.setPassword(encryptedPassword);
                  userRepository.save(newUser);
            } else {
                  throw new BusinessRulesException("Email invalido");
            }

      }



}
