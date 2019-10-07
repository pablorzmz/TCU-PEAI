package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name =  "curso" )
public class Curso implements Serializable {
    private static final long serialVersionUID = 3891558444775224854L;

    public Curso(){}

    public Curso(CursoPK id, String nombre, String descripcion, String foto) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        Foto = foto;
    }

    @EmbeddedId
    private CursoPK Id;

    @Column(name = "nombre")
    private String Nombre;

    @Column( name = "descripcion")
    private String Descripcion;

    @Column( name = "foto" )
    private String Foto;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_tematica_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private AreaTematica areaTematica;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<Grupo> grupos = new ArrayList<>();

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public CursoPK getId() {
        return Id;
    }

    public void setId(CursoPK id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }
}
