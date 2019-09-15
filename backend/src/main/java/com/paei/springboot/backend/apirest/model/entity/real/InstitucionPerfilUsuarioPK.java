package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InstitucionPerfilUsuarioPK implements Serializable {

    private static final long serialVersionUID = 7051038569418160823L;

    private Usuario usuario;

    private Institucion institucion;

    private Perfil perfil;

    @ManyToOne(cascade = CascadeType.ALL)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitucionPerfilUsuarioPK that = (InstitucionPerfilUsuarioPK) o;
        return Objects.equals(usuario, that.usuario) &&
                Objects.equals(institucion, that.institucion) &&
                Objects.equals(perfil, that.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, institucion, perfil);
    }
}
