package com.example.redsocial.mappers;

import org.springframework.stereotype.Component;

import com.example.redsocial.dtos.CreatePublicacionDto;
import com.example.redsocial.dtos.GetPublicacionDto;
import com.example.redsocial.entities.Usuario;
import com.example.redsocial.entities.Publicacion;

@Component
public class PublicacionMappers {
    
    public GetPublicacionDto toGetPublicacionDto(Publicacion publicacion) {
        return new GetPublicacionDto(
          publicacion.getId(),
          publicacion.getUsuario().getId(),
          publicacion.getContenido(),
          publicacion.getFechaHora(),
          publicacion.getFavs());
      }

    public Publicacion toPublicacion(Usuario usuario, CreatePublicacionDto publicacionDto ) {
    return new Publicacion(
        usuario,
        publicacionDto.getContenido()
        );
    }
}
