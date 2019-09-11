package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institucion")
public class Institucion implements Serializable {

    private static final long serialVersionUID = -5494201154230598756L;

    public Institucion(InstitucionPK nombre, String descripcion, String ubicación, Boolean habilitada, String foto) {
        Nombre = nombre;
        Descripcion = descripcion;
        Ubicación = ubicación;
        Habilitada = habilitada;
        Foto = foto;
    }

    @EmbeddedId
    private InstitucionPK Nombre;

    @Column(name = "descripcion")
    private String Descripcion;

    @Column(name = "ubicacion")
    private String Ubicación;

    @Column(name = "habilitada")
    private Boolean Habilitada;

    @Column(name = "foto")
    private String Foto;

    @OneToMany(mappedBy = "institucion")
    private List<AreaTematica> areaTematicas = new ArrayList<>();

    public InstitucionPK getNombre() {
        return Nombre;
    }

    public void setNombre(InstitucionPK nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getUbicación() {
        return Ubicación;
    }

    public void setUbicación(String ubicación) {
        Ubicación = ubicación;
    }

    public Boolean getHabilitada() {
        return Habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        Habilitada = habilitada;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}


