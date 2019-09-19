package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = -4113078676010766568L;

    public Categoria(){}

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "numero", nullable = false, referencedColumnName = "numero"),
            @JoinColumn(name = "curso_nombre", nullable = false, referencedColumnName = "curso_nombre"),
            @JoinColumn(name = "periodo_tiempo",  nullable = false, referencedColumnName = "periodo_tiempo")
    })
    private Grupo grupo;

    @OneToMany(mappedBy = "categoria")
    private List<Material> materiales = new ArrayList<>();


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
