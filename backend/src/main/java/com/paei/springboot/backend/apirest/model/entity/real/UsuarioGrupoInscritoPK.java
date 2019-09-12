package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioGrupoInscritoPK implements Serializable {

    private static final long serialVersionUID = 7246558206899768562L;

    private GrupoPK grupo;

    public GrupoPK getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoPK grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioGrupoInscritoPK that = (UsuarioGrupoInscritoPK) o;
        return Objects.equals(grupo, that.grupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupo);
    }
}
