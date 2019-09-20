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

    public Perfil(){}

    @JsonIgnore
    @OneToMany(mappedBy = "institucionPerfilUsuarioPK.perfil",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<InstitucionPerfilUsuario> institucionPerfilUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "institucionPerfilPermisoPK.perfil",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InstitucionPerfilPermiso> institucionPerfilPermisos = new HashSet<>();

    public Perfil(PerfilPK id, String descripcion, Institucion institucion) {
        Id = id;
        Descripcion = descripcion;
    }

    @Column(name = "nombre")
    private String Nombre;

    @EmbeddedId
    private PerfilPK Id;

    @Column(name = "descripcion")
    private String Descripcion;

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
        this.Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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
