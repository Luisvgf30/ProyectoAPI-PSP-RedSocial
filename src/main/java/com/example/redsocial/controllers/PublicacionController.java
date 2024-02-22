package com.example.redsocial.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.redsocial.dtos.CreatePublicacionDto;
import com.example.redsocial.dtos.GetPublicacionDto;
import com.example.redsocial.entities.Usuario;
import com.example.redsocial.entities.Publicacion;
import com.example.redsocial.exceptions.UsuarioNotFoundException;
import com.example.redsocial.mappers.PublicacionMappers;
import com.example.redsocial.repositories.UsuarioRepository;
import com.example.redsocial.repositories.PublicacionRepository;

import jakarta.validation.Valid;

@RestController
public class PublicacionController {
    // Repositorios de publicacion y usuarios
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    // Mapper de publicas
    private final PublicacionMappers publicacionMappers;

    PublicacionController(
      PublicacionRepository publicacionRepository,
      UsuarioRepository usuarioRepository,
      PublicacionMappers publicacionMappers) {
        this.publicacionRepository = publicacionRepository;
        this.publicacionMappers = publicacionMappers;
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todas las publicaciones de un usuario por su ID
    @GetMapping("/usuarios/{id}/publicaciones")
    List<GetPublicacionDto> getPublicacionesByUsuarioId(@NonNull @Valid @PathVariable Long id) {
        usuarioRepository
            .findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException(id));

        List<Publicacion> listaPublicaciones = publicacionRepository.findByUsuarioId(id);
        List<GetPublicacionDto> listaPublicacionesDto = new ArrayList<GetPublicacionDto>();

        for (Publicacion publicacion : listaPublicaciones) {
        listaPublicacionesDto.add(publicacionMappers.toGetPublicacionDto(publicacion));
        }

        return listaPublicacionesDto;
    }

    // Obtener la publicacion por ID de un usuario por su ID
    @GetMapping("/usuarios/{UsuarioId}/publicaciones/{publicacionId}")
    List<GetPublicacionDto> getPublicacionIdByUsuarioId(@NonNull @Valid @PathVariable Long id) {
        usuarioRepository
            .findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException(id));

        List<Publicacion> listaPublicaciones = publicacionRepository.findByUsuarioId(id);
        List<GetPublicacionDto> listaPublicacionesDto = new ArrayList<GetPublicacionDto>();

        for (Publicacion publicacion : listaPublicaciones) {
            if(publicacion.getId().equals(id)){
                listaPublicacionesDto.add(publicacionMappers.toGetPublicacionDto(publicacion));
            }
        }

        return listaPublicacionesDto;
    }

    // Crear una publicacion nueva para un usuario por su ID
    @PostMapping("/usuarios/{id}/publicaciones")
    GetPublicacionDto postPublicacion(
        @NonNull @Valid @PathVariable Long id,
        @NonNull @Valid @RequestBody CreatePublicacionDto nuevaPublicacion) {
        Usuario usuario = usuarioRepository
            .findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException(id));

        Publicacion publicacionGuardada = publicacionRepository.save(
            Objects.requireNonNull(publicacionMappers.toPublicacion(usuario, nuevaPublicacion)));

        GetPublicacionDto publicacionGuardadaDto = publicacionMappers.toGetPublicacionDto(publicacionGuardada);

        return publicacionGuardadaDto;
    }
}
