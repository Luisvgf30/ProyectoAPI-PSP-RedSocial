package com.example.redsocial.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.redsocial.dtos.CreatePublicacionDto;
import com.example.redsocial.dtos.GetPublicacionDto;
import com.example.redsocial.entities.Usuario;
import com.example.redsocial.entities.Publicacion;
import com.example.redsocial.exceptions.PublicacionNotFoundException;
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
    List<GetPublicacionDto> getPublicacionesByUsuarioId(@NonNull @Valid @PathVariable UUID id) {
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
    List<GetPublicacionDto> getPublicacionIdByUsuarioId(@NonNull @Valid @PathVariable UUID UsuarioId, @NonNull @Valid @PathVariable UUID publicacionId) {
        usuarioRepository
            .findById(UsuarioId)
            .orElseThrow(() -> new UsuarioNotFoundException(UsuarioId));

        List<Publicacion> listaPublicaciones = publicacionRepository.findByUsuarioId(UsuarioId);
        List<GetPublicacionDto> listaPublicacionesDto = new ArrayList<GetPublicacionDto>();

        for (Publicacion publicacion : listaPublicaciones) {
            if(publicacion.getId().equals(publicacionId)){
                listaPublicacionesDto.add(publicacionMappers.toGetPublicacionDto(publicacion));
            }
        }

        return listaPublicacionesDto;
    }

    // Crear una publicacion nueva para un usuario por su ID
    @PostMapping("/usuarios/{id}/publicaciones")
    GetPublicacionDto postPublicacion(
      @NonNull @Valid @PathVariable UUID id,
      @NonNull @Valid @RequestBody CreatePublicacionDto nuevaPublicacion) {
    Usuario usuario = usuarioRepository
        .findById(id)
        .orElseThrow(() -> new UsuarioNotFoundException(id));

    Publicacion publicacionGuardada = publicacionRepository.save(
        Objects.requireNonNull(publicacionMappers.toPublicacion(usuario, nuevaPublicacion)));

    GetPublicacionDto publicacionGuardadaDto = publicacionMappers.toGetPublicacionDto(publicacionGuardada);

    return publicacionGuardadaDto;
  }

    /// Actualizar una publicacion existente para un usuario por su ID
    @PatchMapping("/usuarios/{id}/publicaciones/{publicacionId}")
    GetPublicacionDto patchPublicacion(
    @NonNull @Valid @PathVariable UUID id,
    @NonNull @Valid @PathVariable UUID publicacionId,
    @NonNull @Valid @RequestBody GetPublicacionDto publicacionActualizada) {

    usuarioRepository
        .findById(id)
        .orElseThrow(() -> new UsuarioNotFoundException(id));

    Publicacion publicacionExistente = publicacionRepository
        .findById(publicacionId)
        .orElseThrow(() -> new PublicacionNotFoundException(publicacionId));

    // Actualizar los campos de la publicación con los valores proporcionados
    if (publicacionActualizada.getContenido() != null) {
        publicacionExistente.setContenido(publicacionActualizada.getContenido());
    }

    // Guardar la publicación actualizada
    Publicacion publicacionActu = publicacionRepository.save(publicacionExistente);

    GetPublicacionDto publicacionActualizadaDto = publicacionMappers.toGetPublicacionDto(publicacionActu);

    return publicacionActualizadaDto;
    }

    // Borrar una publicacion de un usuario por su ID
    @DeleteMapping("/usuarios/{usuarioId}/publicaciones/{publicacionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePublicacionById(@NonNull @PathVariable UUID usuarioId, 
    @NonNull @PathVariable UUID publicacionId) {
        usuarioRepository
            .findById(usuarioId)
            .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));

       publicacionRepository
        .findById(publicacionId)
        .orElseThrow(() -> new PublicacionNotFoundException(publicacionId));

        //Recojo la lista para controlar el error de no borrar una publicacion que no pertenezca a ese usuario
        List<Publicacion> listaPublicaciones = publicacionRepository.findByUsuarioId(usuarioId);

        for (Publicacion publicacion : listaPublicaciones) {
            if(publicacion.getId().equals(publicacionId)){
                publicacionRepository.deleteById(publicacion.getId());
            }
        }
        
    }

}
