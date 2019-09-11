package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = -4113078676010766568L;

    public Categoria(CategoriaPK id, String nombre, String descripcion) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    @EmbeddedId
    private CategoriaPK Id;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "descripcion")
    private String Descripcion;

    public CategoriaPK getId() {
        return Id;
    }

    public void setId(CategoriaPK id) {
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
}
