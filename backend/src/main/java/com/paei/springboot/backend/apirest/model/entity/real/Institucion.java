package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "institucion")
public class Institucion implements Serializable {

    private static final long serialVersionUID = -5494201154230598756L;

    public Institucion(){}

    @OneToMany(mappedBy = "institucionPerfilUsuarioPK.institucion",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InstitucionPerfilUsuario> institucionPerfilUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "institucionPerfilPermisoPK.institucion",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InstitucionPerfilPermiso> institucionPerfilPermisos = new HashSet<>();

    public Institucion(InstitucionPK nombre, String descripcion, String ubicación, Boolean habilitada, String foto) {
        institucionPK = nombre;
        Descripcion = descripcion;
        Ubicación = ubicación;
        Habilitada = habilitada;
        Foto = foto;
    }

    @EmbeddedId
    private InstitucionPK institucionPK;

    @Column(name = "descripcion")
    private String Descripcion;

    @Column(name = "ubicacion")
    private String Ubicación;

    @Column(name = "habilitada")
    private Boolean Habilitada;

    @Column(name = "foto")
    private String Foto;

    @OneToMany(mappedBy = "institucion", fetch = FetchType.LAZY)
    @JsonIgnore // --> para evitar JSON recursivo
    private List<AreaTematica> areaTematicas = new ArrayList<>();

    public InstitucionPK getInstitucionPK() {
        return institucionPK;
    }

    public void setInstitucionPK(InstitucionPK institucionPK) {
        this.institucionPK = institucionPK;
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

    public Set<InstitucionPerfilUsuario> getInstitucionPerfilUsuarios() {
        return institucionPerfilUsuarios;
    }

    public void setInstitucionPerfilUsuarios(Set<InstitucionPerfilUsuario> institucionPerfilUsuarios) {
        this.institucionPerfilUsuarios = institucionPerfilUsuarios;
    }

    public Set<InstitucionPerfilPermiso> getInstitucionPerfilPermisos() {
        return institucionPerfilPermisos;
    }

    public void setInstitucionPerfilPermisos(Set<InstitucionPerfilPermiso> institucionPerfilPermisos) {
        this.institucionPerfilPermisos = institucionPerfilPermisos;
    }

    public List<AreaTematica> getAreaTematicas() {
        return areaTematicas;
    }

    public void setAreaTematicas(List<AreaTematica> areaTematicas) {
        this.areaTematicas = areaTematicas;
    }


}


