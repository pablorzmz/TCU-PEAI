package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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

    public Grupo(){}

    public Grupo(GrupoPK grupoPK, Usuario usuario, Curso curso){
        this.Id = grupoPK;
        this.usuario = usuario;
        this.curso = curso;
    }

    @EmbeddedId
    private GrupoPK Id;

    public GrupoPK getId() {
        return Id;
    }

    public void setId(GrupoPK id) {
        Id = id;
    }

    @MapsId("cursoId")
    @JoinColumns({
            @JoinColumn(name="curso_id", referencedColumnName="curso_id")
    })
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<SubseccionEvaluacion> subseccionEvaluaciones = new ArrayList<>();

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<SubseccionMaterial> subseccionMateriales = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_usuario_imparte")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Usuario usuario;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UsuarioGrupoInscrito> usuarioGrupoInscritos = new ArrayList<>();

    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
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
