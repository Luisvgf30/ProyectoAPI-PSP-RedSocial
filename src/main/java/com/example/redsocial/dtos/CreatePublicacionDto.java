package com.example.redsocial.dtos;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.redsocial.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePublicacionDto {
    
    @NotBlank(message = "contenido es obligatorio")
    private String contenido;

    public CreatePublicacionDto() {}

    public CreatePublicacionDto(String contenido) {
        this.contenido = contenido;
    }
}
