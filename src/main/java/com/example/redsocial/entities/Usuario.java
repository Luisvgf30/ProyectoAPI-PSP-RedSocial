package com.example.redsocial.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;    

    @NotBlank(message = "apodo es obligatorio")
    private String apodo;
  
    @NotNull(message = "fechaNacimiento es obligatoria")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotNull(message = "numSeguidores es obligatorio")
    private Integer numSeguidores;

    @NotNull(message = "boolean es obligatorio")
    private Boolean bloqueado;
  
    public Usuario() {}

    public Usuario(String apodo, Date fechaNacimiento, Integer numSeguidores, Boolean bloqueado) {
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.numSeguidores = numSeguidores;
        this.bloqueado = bloqueado;
    }
  
    
}
