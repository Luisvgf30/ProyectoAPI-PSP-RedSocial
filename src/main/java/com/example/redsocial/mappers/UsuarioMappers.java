package com.example.redsocial.mappers;

import org.springframework.stereotype.Component;

import com.example.redsocial.dtos.CreateUsuarioDto;
import com.example.redsocial.entities.Usuario;

import io.micrometer.common.lang.NonNull;

@Component
public class UsuarioMappers {
    @NonNull
    public Usuario toUsuario(@NonNull CreateUsuarioDto usuarioDto) {
        return new Usuario(
            usuarioDto.getApodo(),
            usuarioDto.getFechaNacimiento());
      }
}
