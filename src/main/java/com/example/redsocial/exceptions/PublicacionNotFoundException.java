package com.example.redsocial.exceptions;

import java.util.UUID;

public class PublicacionNotFoundException extends RuntimeException {

  public PublicacionNotFoundException(UUID id) {
    super("Publicación con ID " + id + " no encontrado");
  }
}
