package com.example.redsocial.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redsocial.entities.Usuario;

public interface UsuarioRepository 
    extends JpaRepository<Usuario, UUID> {

}

    
    
