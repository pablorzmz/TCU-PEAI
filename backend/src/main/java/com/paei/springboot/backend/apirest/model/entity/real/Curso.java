package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name =  "curso" )
public class Curso implements Serializable {
    private static final long serialVersionUID = 3891558444775224854L;

    public Curso(){}

    public Curso(CursoPK id, String descripcion, String foto) {
        Id = id;
        Descripcion = descripcion;
        Foto = foto;
    }

    @EmbeddedId
    private CursoPK Id;

    @Column( name = "descripcion")
    private String Descripcion;

    @Column( name = "foto" )
    private String Foto;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_tematica_id")
    private AreaTematica areaTematica;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public CursoPK getId() {
        return Id;
    }

    public void setId(CursoPK id) {
        Id = id;
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
