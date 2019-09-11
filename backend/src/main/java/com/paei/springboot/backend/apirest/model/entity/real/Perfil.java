package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfil")
public class Perfil  implements Serializable {

    private static final long serialVersionUID = -2058982657833793266L;

    public Perfil(PerfilPK id, String nombre, String descripcion, Institucion institucion) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        this.institucion = institucion;
    }

    @OneToMany(mappedBy = "perfilUsuario.perfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PerfilUsuario> perfilUsuario = new HashSet<>();

    @OneToMany(mappedBy = "perfilPermiso.perfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PerfilPermiso> perfilPermiso = new HashSet<>();

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

    public Set<PerfilUsuario> getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Set<PerfilUsuario> perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public Set<PerfilPermiso> getPerfilPermiso() {
        return perfilPermiso;
    }

    public void setPerfilPermiso(Set<PerfilPermiso> perfilPermiso) {
        this.perfilPermiso = perfilPermiso;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
}
