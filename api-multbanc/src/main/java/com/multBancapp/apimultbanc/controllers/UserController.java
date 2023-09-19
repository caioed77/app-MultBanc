package com.multBancapp.apimultbanc.controllers;

import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.models.dto.UpdateUserDTO;
import com.multBancapp.apimultbanc.models.dto.UserDTO;
import com.multBancapp.apimultbanc.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Usuarios", description = "End points de usuarios")
@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

      private final UserService userService;

      public UserController(UserService userService) {
            this.userService = userService;
      }

      @GetMapping(value = "/documento")
      public ResponseEntity<UserDTO> findDocument(@RequestParam Optional<String> documento) {
            return documento.map(s -> ResponseEntity.ok(userService.findUser(s)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
      }

      @PatchMapping(value = "/atualizar/{id}")
      @ResponseStatus(HttpStatus.ACCEPTED)
      public ResponseEntity<Void> updateUser(
              @PathVariable Long id,
              @RequestBody UpdateUserDTO updatedUser) {
            userService.changeUser(id, updatedUser);
            return ResponseEntity.ok().build();
      }
}
