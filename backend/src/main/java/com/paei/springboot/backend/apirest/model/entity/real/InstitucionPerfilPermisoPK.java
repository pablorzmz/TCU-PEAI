package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InstitucionPerfilPermisoPK implements Serializable {

    private static final long serialVersionUID = 2977759630309106950L;

    private Institucion institucion;

    private Perfil perfil;

    private Permiso permiso;

    @ManyToOne(cascade = CascadeType.ALL)
    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitucionPerfilPermisoPK that = (InstitucionPerfilPermisoPK) o;
        return Objects.equals(institucion, that.institucion) &&
                Objects.equals(perfil, that.perfil) &&
                Objects.equals(permiso, that.permiso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institucion, perfil, permiso);
    }
}
