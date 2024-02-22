package com.example.redsocial;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.redsocial.entities.Usuario;
import com.example.redsocial.entities.Publicacion;
import com.example.redsocial.repositories.UsuarioRepository;
import com.example.redsocial.repositories.PublicacionRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {

    Usuario usuario1 = new Usuario("luis", new Date(), 5000, true);
    Usuario usuario2 = new Usuario("raul", new Date(), 400, false);
    Usuario usuario3 = new Usuario("ricardo", new Date(), 2300, true);

    return args -> {
      log.info("Preloading " + usuarioRepository.save(usuario1));
      log.info("Preloading " + usuarioRepository.save(usuario2));
      log.info("Preloading " + usuarioRepository.save(usuario3));
      
      log.info("Preloading " + publicacionRepository.save(new Publicacion(usuario1, "Un dia en la playa", new Date(), 1000)));
      log.info("Preloading " + publicacionRepository.save(new Publicacion(usuario2, "Un dia en la piscina", new Date(), 300)));
      log.info("Preloading " + publicacionRepository.save(new Publicacion(usuario3, "Un dia en Sevilla", new Date(), 700)));
      log.info("Preloading " + publicacionRepository.save(new Publicacion(usuario1, "Un dia en la montaña", new Date(), 1400)));
    };
  }
}
