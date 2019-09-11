package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "permiso")
public class Permiso implements Serializable {

    private static final long serialVersionUID = 5786995689259432153L;

    public Permiso(PermisoPK id, String nombre) {
        Id = id;
        Nombre = nombre;
    }

    @OneToMany(mappedBy = "perfilPermiso.permiso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PerfilPermiso> perfilPermiso = new HashSet<>();

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

    public Set<PerfilPermiso> getPerfilPermiso() {
        return perfilPermiso;
    }

    public void setPerfilPermiso(Set<PerfilPermiso> perfilPermiso) {
        this.perfilPermiso = perfilPermiso;
    }
}
