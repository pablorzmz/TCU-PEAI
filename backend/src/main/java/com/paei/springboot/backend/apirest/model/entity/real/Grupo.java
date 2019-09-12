package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 5927864906117084238L;

    @EmbeddedId
    private GrupoPK Id;

    public GrupoPK getId() {
        return Id;
    }

    public void setId(GrupoPK id) {
        Id = id;
    }

    @MapsId("curso_id")
    @JoinColumns({
            @JoinColumn(name="institucion_nombre", referencedColumnName="institucion_nombre"),
            @JoinColumn(name="area_tematica_id", referencedColumnName="area_tematica_id"),
            @JoinColumn(name="curso_nombre", referencedColumnName="curso_nombre")
    })
    @ManyToOne
    private Curso curso;


    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_usuario_imparte")
    private Usuario usuario;

}
