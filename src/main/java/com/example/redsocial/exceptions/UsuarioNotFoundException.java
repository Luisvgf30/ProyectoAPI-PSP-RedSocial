package com.example.redsocial.exceptions;

public class UsuarioNotFoundException extends RuntimeException {

  public UsuarioNotFoundException(Long id) {
    super("No se encontró el usuario " + id);
  }
}
