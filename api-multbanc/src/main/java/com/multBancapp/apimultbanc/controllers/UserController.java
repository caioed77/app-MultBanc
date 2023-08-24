package com.multBancapp.apimultbanc.controllers;

import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.models.dto.UserDTO;
import com.multBancapp.apimultbanc.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Tag(name = "Usuarios", description = "End points de usuarios")
@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

      private final UserService userService;

      @Autowired
      public UserController(UserService userService) {
            this.userService = userService;
      }


      @Operation(
              summary = "Lista usuarios por documento(cpf)",
              description = "Devolve o usuario pelo documento informado",
              tags = { "documento", "get" })
      @ApiResponses({
              @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/json") }),
              @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
              @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
      @GetMapping(value = "/documento")
      public ResponseEntity<UserDTO> findDocument(@RequestParam Optional<String> documento) {
            return documento.map(s -> ResponseEntity.ok(userService.findUser(s)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
      }

      @Operation(
              summary = "Cria usuario",
              description = "Cadastrar o usuario",
              tags = { "registrar", "post" })
      @ApiResponses({
              @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/json") }),
              @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
              @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
      @PostMapping(value = "/registrar")
      @ResponseStatus(HttpStatus.CREATED)
      public ResponseEntity<UserEntity> createUser(@RequestBody  UserEntity user) {
            return ResponseEntity.ok().body(userService.createUser(user));
      }
}
