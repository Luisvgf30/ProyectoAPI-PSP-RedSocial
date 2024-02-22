package com.example.redsocial.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUsuarioDto {
    @NotBlank(message = "apodo es obligatorio")
    private String apodo;
  
    @NotNull(message = "fechaNacimiento es obligatoria")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotNull(message = "numSeguidores es obligatorio")
    private Integer numSeguidores;

    @NotNull(message = "bloquear es obligatorio")
    private Boolean bloqueado;
  
    public CreateUsuarioDto() {}

    public CreateUsuarioDto(String apodo, Date fechaNacimiento, Integer numSeguidores, Boolean bloqueado) {
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.numSeguidores = numSeguidores;
        this.bloqueado = bloqueado;
    }
}
