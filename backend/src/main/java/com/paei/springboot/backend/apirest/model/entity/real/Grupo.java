package com.paei.springboot.backend.apirest.model.entity.real;

import com.paei.springboot.backend.apirest.model.entity.foo.Tabla123;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            @JoinColumn(name="curso_nombre", referencedColumnName="curso_nombre")
    })
    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "grupo")
    private List<SubseccionEvaluacion> subseccionEvaluaciones = new ArrayList<>();

    @OneToMany(mappedBy = "grupo")
    private List<SubseccionMaterial> subseccionMateriales = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_usuario_imparte")
    private Usuario usuario;

    @OneToMany(mappedBy = "grupo")
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "grupo")
    private List<UsuarioGrupoInscrito> usuarioGrupoInscritos = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "usuarioGrupoInscritoPK.grupo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsuarioGrupoInscrito> usuarioGrupoInscritos = new HashSet<>();

    public Set<UsuarioGrupoInscrito> getUsuarioGrupoInscritos() {
        return usuarioGrupoInscritos;
    }

    public void setUsuarioGrupoInscritos(Set<UsuarioGrupoInscrito> usuarioGrupoInscritos) {
        this.usuarioGrupoInscritos = usuarioGrupoInscritos;
    }
    */

}
