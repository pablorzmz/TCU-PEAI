package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name= "subseccion_material")
public class SubseccionMaterial implements Serializable {
    private static final long serialVersionUID = 2521005430944303873L;

    public SubseccionMaterial(){}

    @Id
    @Column(name = "subseccion_material_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column( name = "nombre")
    private String Nombre;

    @Column( name = "habilitada")
    private Boolean Habilitada;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "numero", unique = false, nullable = false, referencedColumnName = "numero"),
            @JoinColumn(name = "curso_id", unique = false, nullable = false, referencedColumnName = "curso_id"),
            @JoinColumn(name = "periodo_tiempo", unique = false, nullable = false, referencedColumnName = "periodo_tiempo")
    })
    private Grupo grupo;

    @OneToMany(mappedBy = "subseccionMaterial", fetch = FetchType.LAZY)
    private List<Material> materiales = new ArrayList<>();

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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
