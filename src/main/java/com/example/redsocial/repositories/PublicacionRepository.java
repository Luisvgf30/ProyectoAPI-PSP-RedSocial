package com.example.redsocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redsocial.entities.Publicacion;


public interface PublicacionRepository
  extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByUsuarioId(Long usuarioId);

}
