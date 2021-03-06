package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "subseccion_evaluacion")
public class SubseccionEvaluacion implements Serializable
{
    private static final long serialVersionUID = 5314742781555961959L;

    public SubseccionEvaluacion(SubseccionEvaluacionPK id, String nombre, Boolean habilitada) {
        Id = id;
        Nombre = nombre;
        Habilitada = habilitada;
    }

    @EmbeddedId
    private SubseccionEvaluacionPK Id;

    @Column( name = "nombre")
    private String Nombre;

    @Column( name = "habilitada")
    private Boolean Habilitada;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "numero", unique = true, nullable = false, referencedColumnName = "numero"),
            @JoinColumn(name = "area_tematica_id", unique = true, nullable = false, referencedColumnName = "area_tematica_id"),
            @JoinColumn(name = "curso_nombre", unique = true, nullable = false, referencedColumnName = "curso_nombre"),
            @JoinColumn(name = "institucion_nombre", unique = true, nullable = false, referencedColumnName = "institucion_nombre"),
            @JoinColumn(name = "periodo_tiempo", unique = true, nullable = false, referencedColumnName = "periodo_tiempo")
    })
    private Grupo grupo;

    @OneToMany(mappedBy = "subseccionEvaluacion")
    private List<Evaluacion> evaluaciones = new ArrayList<>();


    public SubseccionEvaluacionPK getId() {
        return Id;
    }

    public void setId(SubseccionEvaluacionPK id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Boolean getHabilitada() {
        return Habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        Habilitada = habilitada;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
