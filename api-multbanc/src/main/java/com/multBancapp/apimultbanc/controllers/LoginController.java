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

      public LoginController(UserService userService) {
            this.userService = userService;
      }

      @GetMapping(value = "/autenticar")
      public ResponseEntity<UserDTO> validadeLogin(@RequestParam String email, @RequestParam String senha) {
            if (userService.isValid(email, senha))
                  return new ResponseEntity<>(userService.findUser(email), HttpStatus.ACCEPTED);

            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
      }

      @PostMapping(value = "/novoUsuario")
      @ResponseStatus(HttpStatus.CREATED)
      public ResponseEntity<Void> registerLogin(@RequestBody UserDTO userDTO) {
            userService.registerLogin(userDTO);
            return  ResponseEntity.ok().build();
      }

}
