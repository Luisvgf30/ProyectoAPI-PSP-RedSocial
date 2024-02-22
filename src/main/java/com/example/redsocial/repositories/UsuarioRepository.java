package com.example.redsocial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redsocial.entities.Usuario;

public interface UsuarioRepository 
    extends JpaRepository<Usuario, Long> {

}

    
    
