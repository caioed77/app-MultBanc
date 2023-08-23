package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.entities.UserEntity;
import com.multBancapp.apimultbanc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

      private final UserService userService;

      @Autowired
      public LoginController(UserService userService) {
            this.userService = userService;
      }


}
