package com.example.redsocial.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.redsocial.dtos.CreateUsuarioDto;
import com.example.redsocial.entities.Usuario;
import com.example.redsocial.exceptions.UsuarioNotFoundException;
import com.example.redsocial.mappers.UsuarioMappers;
import com.example.redsocial.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {
    // Logger para imprimir por pantalla trazas (log.info(string))
  // private static final Logger log =
  // LoggerFactory.getLogger(EmpleadoController.class);

  // Repositorio de usuarios
  private final UsuarioRepository repository;

  // Mapper de usuarios
  private final UsuarioMappers mapper;

  UsuarioController(UsuarioRepository repository, UsuarioMappers mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  // Obtener lista de usuarios
  @GetMapping("/usuarios")
  List<Usuario> getUsuarios() {
    return repository.findAll();
  }

  // Crear un nuevo usuario
  @PostMapping("/usuarios")
  @ResponseStatus(code = HttpStatus.CREATED)
  Usuario postUsuario(@NonNull @Valid @RequestBody CreateUsuarioDto nuevoUsuarioDto) {
    Usuario usuario = mapper.toUsuario(nuevoUsuarioDto);
    return repository.save(Objects.requireNonNull(usuario));
  }

  // Obtener un usuario por su ID (si existe)
  @GetMapping("/usuarios/{id}")
  Usuario getUsuarioById(@NonNull @PathVariable Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new UsuarioNotFoundException(id));
  }

  // Actualizar un usuario existente (si no existe se crea uno nuevo)
  @PutMapping("/usuarios/{id}")
  ResponseEntity<Usuario> putEmpleadoById(
      @NonNull @Valid @RequestBody CreateUsuarioDto nuevoUsuarioDto,
      @NonNull @PathVariable Long id) {
    return repository
        .findById(id)
        .map(usuario -> {
            usuario.setApodo(nuevoUsuarioDto.getApodo());
            usuario.setFechaNacimiento(nuevoUsuarioDto.getFechaNacimiento());
            usuario.setNumSeguidores(nuevoUsuarioDto.getNumSeguidores());
            usuario.setBloqueado(nuevoUsuarioDto.getBloqueado());

        
          return new ResponseEntity<Usuario>(repository.save(usuario), HttpStatus.OK);
        })
        .orElseGet(() -> {
          Usuario nuevoUsuario = mapper.toUsuario(nuevoUsuarioDto);
          return new ResponseEntity<Usuario>(repository.save(Objects.requireNonNull(nuevoUsuario)),
              HttpStatus.CREATED);
        });
  }

  // Borrar un usuario por su ID
  @DeleteMapping("/empleados/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteEmpleadoById(@NonNull @PathVariable Long id) {
    repository.deleteById(id);
  }
}
