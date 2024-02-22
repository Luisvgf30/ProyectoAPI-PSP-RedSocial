package com.example.redsocial.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Publicacion {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @NotBlank(message = "contenido es obligatorio")
    private String contenido;

    @NotNull(message = "fechaNacimiento es obligatoria")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @NotNull(message = "favs es obligatorio")
    private Integer favs;

    public Publicacion() {}

    public Publicacion(Usuario usuario, String contenido, Date fechaHora, Integer favs) {
        this.usuario = usuario;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.favs = favs;
    }

    
}
