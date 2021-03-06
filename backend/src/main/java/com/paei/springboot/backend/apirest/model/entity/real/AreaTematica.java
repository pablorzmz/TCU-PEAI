package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "area_tematica")
public class AreaTematica implements Serializable {
    private static final long serialVersionUID = 1959919138250916358L;

    public AreaTematica(AreaTematicaPK id, String nombre, String descripcion, SiglaTematica siglaTematica, Institucion institucion) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        this.siglaTematica = siglaTematica;
        this.institucion = institucion;
    }

    @EmbeddedId
    private AreaTematicaPK Id;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "descripcion")
    private String Descripcion;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sigla_tematica_id")
    private SiglaTematica siglaTematica;

    @MapsId("institucion_nombre")
    @JoinColumns({
            @JoinColumn(name="institucion_nombre", referencedColumnName="institucion_nombre")
    })
    @ManyToOne
    private Institucion institucion;

    @OneToMany(mappedBy = "areaTematica")
    private List<Curso> cursos = new ArrayList<>();


    public AreaTematicaPK getId() {
        return Id;
    }

    public void setId(AreaTematicaPK id) {
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

    public SiglaTematica getSiglaTematica() {
        return siglaTematica;
    }

    public void setSiglaTematica(SiglaTematica siglaTematica) {
        this.siglaTematica = siglaTematica;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
}
