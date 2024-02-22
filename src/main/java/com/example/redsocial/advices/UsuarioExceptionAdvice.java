package com.example.redsocial.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.redsocial.exceptions.UsuarioNotFoundException;

@RestControllerAdvice
public class UsuarioExceptionAdvice {
    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    String empleadoNotFoundHandler(UsuarioNotFoundException ex) {
    return ex.getMessage();
  }
}
