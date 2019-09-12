package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name= "subseccion_material")
public class SubseccionMaterial implements Serializable {
    private static final long serialVersionUID = 2521005430944303873L;

    public SubseccionMaterial(SubSeccionMaterialPK id, String nombre, Boolean habilitada) {
        Id = id;
        Nombre = nombre;
        Habilitada = habilitada;
    }

    @EmbeddedId
    private SubSeccionMaterialPK Id;

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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public SubSeccionMaterialPK getId() {
        return Id;
    }

    public void setId(SubSeccionMaterialPK id) {
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
}
