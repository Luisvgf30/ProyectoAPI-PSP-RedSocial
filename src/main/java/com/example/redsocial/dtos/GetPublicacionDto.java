package com.example.redsocial.dtos;

import java.util.Date;
import java.util.UUID;

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
public class GetPublicacionDto {
    private UUID id;
    private UUID usuario;
    private String contenido;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    private Integer favs;


    public GetPublicacionDto() {}


    public GetPublicacionDto(UUID id, UUID usuario, String contenido, Date fechaHora, Integer favs) {
        this.id = id;
        this.usuario = usuario;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.favs = favs;
    }

    
}
