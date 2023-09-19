package com.multBancapp.apimultbanc.services;

import com.multBancapp.apimultbanc.config.AuthSecurityConfig;
import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.entities.enums.TypePerson;
import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.exceptions.ResouceNotFoundException;
import com.multBancapp.apimultbanc.models.dto.UpdateUserDTO;
import com.multBancapp.apimultbanc.models.dto.UserDTO;
import com.multBancapp.apimultbanc.repositories.UserRepository;
import com.multBancapp.apimultbanc.services.validations.ValidateCPF;
import com.multBancapp.apimultbanc.services.validations.ValidateEmail;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

      private final UserRepository userRepository;

      @Autowired
      private AuthSecurityConfig passwordEncoder;

      public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      @Transactional(readOnly = true)
      public Optional<UserEntity> findById(Long id) {
            return userRepository.findById(id);
      }

      public UserEntity findByDocument(String document) {
            Optional<UserEntity> result = Optional.ofNullable(userRepository.findDocument(document));
            return result.orElseThrow(() -> new BusinessRulesException("Usuário não encotrado."));
      }

      @Transactional(readOnly = true)
      public UserDTO findUser(String email) {
            var resultDocument = Optional.of(userRepository.findByEmail(email).get())
                    .orElseThrow(() -> new BusinessRulesException("Usuário não encotrado."));

            TypePerson personType = TypePerson.FISICA;
            String personTypeString = personType.name();

            return new UserDTO(
                    resultDocument.getEmail(),
                    resultDocument.getPassword(),
                    resultDocument.getDocument(),
                    personTypeString,
                    resultDocument.getImgUser(),
                    resultDocument.getId()
            );
      }

      public boolean isValid(String email, String password) {
            var userResult = Optional.ofNullable(userRepository.findByEmail(email))
                    .orElseThrow(() -> new BusinessRulesException("Usuário não encontrado"));
            var comparePassword = passwordEncoder.passwordEncoder().matches(password, userResult.get().getPassword());
            return comparePassword && !userResult.get().getBlocked().contentEquals("T");

      }

      @Transactional
      public void registerLogin(UserDTO userDTO) {
            var validateEmail = new ValidateEmail();

            if (validateEmail.validate(userDTO.email())) {
                  var encryptedPassword = passwordEncoder.passwordEncoder().encode(userDTO.password());
                  var newUser = UserEntity.builder()
                          .email(userDTO.email())
                          .password(encryptedPassword)
                          .blocked("F")
                          .build();

                  userRepository.save(newUser);
            } else {
                  throw new BusinessRulesException("Email invalido");
            }
      }

      @Transactional
      public void changeUser(Long id, UpdateUserDTO obj) {
            try {
                  var validadeCPF = new ValidateCPF();
                  UserEntity dadosAtt = userRepository.getReferenceById(id);

                  if (validadeCPF.validade(obj)) {
                        dadosAtt.setDocument(obj.document());
                        dadosAtt.setTypePerson(TypePerson.valueOf(obj.typePerson().toUpperCase()));
                        dadosAtt.setImgUser(obj.imgUser());
                        userRepository.save(dadosAtt);
                  } else {
                        throw new BusinessRulesException("CPF Invalido");
                  }
            } catch (EntityNotFoundException e) {
                  throw new ResouceNotFoundException(id);
            }
      }


}
