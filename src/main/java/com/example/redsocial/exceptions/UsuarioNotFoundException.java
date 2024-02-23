package com.example.redsocial.exceptions;

import java.util.UUID;

import com.example.redsocial.entities.Usuario;

public class UsuarioNotFoundException extends RuntimeException {

  public UsuarioNotFoundException(UUID id) {
    super("Usuario con ID " + id + " no encontrado");
  }
}
