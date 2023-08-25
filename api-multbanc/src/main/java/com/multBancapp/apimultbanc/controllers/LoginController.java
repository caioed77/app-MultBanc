package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.models.dto.UserDTO;
import com.multBancapp.apimultbanc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

      private final UserService userService;

      @Autowired
      public LoginController(UserService userService) {
            this.userService = userService;
      }


      @GetMapping(value = "/autenticar")
      public ResponseEntity<Boolean> validadeLogin(@RequestParam String email, @RequestParam String senha) {
            if (userService.isValid(email, senha))
                  return new ResponseEntity<>(true, HttpStatus.ACCEPTED);

            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
      }

      @PostMapping(value = "/novoUsuario")
      @ResponseStatus(HttpStatus.CREATED)
      public ResponseEntity registerLogin(@RequestBody UserDTO userDTO) {
            userService.registerLogin(userDTO);
            return  ResponseEntity.ok().build();
      }

}
