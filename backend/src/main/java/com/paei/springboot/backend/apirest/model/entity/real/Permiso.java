package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "permiso")
public class Permiso implements Serializable {

    private static final long serialVersionUID = 5786995689259432153L;

    @OneToMany(mappedBy = "institucionPerfilPermisoPK.permiso",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InstitucionPerfilPermiso> institucionPerfilPermisos = new HashSet<>();

    public Permiso(PermisoPK id, String nombre) {
        Id = id;
        Nombre = nombre;
    }

    @EmbeddedId
    private PermisoPK Id;

    @Column(name = "nombre")
    private String Nombre;

    public PermisoPK getId() {
        return Id;
    }

    public void setId(PermisoPK id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Set<InstitucionPerfilPermiso> getInstitucionPerfilPermisos() {
        return institucionPerfilPermisos;
    }

    public void setInstitucionPerfilPermisos(Set<InstitucionPerfilPermiso> institucionPerfilPermisos) {
        this.institucionPerfilPermisos = institucionPerfilPermisos;
    }
}
