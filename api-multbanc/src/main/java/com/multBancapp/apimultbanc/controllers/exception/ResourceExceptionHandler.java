package com.multBancapp.apimultbanc.controllers.exception;


import com.multBancapp.apimultbanc.exceptions.BusinessRulesException;
import com.multBancapp.apimultbanc.exceptions.ResourceAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

      @ExceptionHandler(BusinessRulesException.class)
      public ResponseEntity<StandardError> objectNotFound(BusinessRulesException e, HttpServletRequest request) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Recurso Já Existe", e.getMessage(), request.getRequestURI());

            return ResponseEntity.status(status).body(err);
      }


      @ExceptionHandler(ResourceAlreadyExistsException.class)
      public ResponseEntity<StandardError> resourceAlreadyExists(ResourceAlreadyExistsException e, HttpServletRequest request) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não Encontrado", e.getMessage(), request.getRequestURI());

            return ResponseEntity.status(status).body(err);

      }
}