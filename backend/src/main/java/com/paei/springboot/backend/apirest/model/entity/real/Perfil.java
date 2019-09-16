package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfil")
public class Perfil  implements Serializable {

    private static final long serialVersionUID = -2058982657833793266L;

    @JsonIgnore
    @OneToMany(mappedBy = "institucionPerfilUsuarioPK.perfil",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<InstitucionPerfilUsuario> institucionPerfilUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "institucionPerfilPermisoPK.perfil",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InstitucionPerfilPermiso> institucionPerfilPermisos = new HashSet<>();

    public Perfil(PerfilPK id, String nombre, String descripcion, Institucion institucion) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        this.institucion = institucion;
    }

    @EmbeddedId
    private PerfilPK Id;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "descripcion")
    private String Descripcion;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Institucion institucion;


    public PerfilPK getId() {
        return Id;
    }

    public void setId(PerfilPK id) {
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

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Set<InstitucionPerfilUsuario> getInstitucionPerfilUsuarios() {
        return institucionPerfilUsuarios;
    }

    public Set<InstitucionPerfilPermiso> getInstitucionPerfilPermisos() {
        return institucionPerfilPermisos;
    }

    public void setInstitucionPerfilPermisos(Set<InstitucionPerfilPermiso> institucionPerfilPermisos) {
        this.institucionPerfilPermisos = institucionPerfilPermisos;
    }

    public void setInstitucionPerfilUsuarios(Set<InstitucionPerfilUsuario> institucionPerfilUsuarios) {
        this.institucionPerfilUsuarios = institucionPerfilUsuarios;
    }
}
