package com.example.redsocial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redsocial.entities.Publicacion;


public interface PublicacionRepository
  extends JpaRepository<Publicacion, UUID> {
    List<Publicacion> findByUsuarioId(UUID usuarioId);
  }

