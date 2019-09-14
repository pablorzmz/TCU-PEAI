package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioGrupoInscritoPK implements Serializable {

    private static final long serialVersionUID = 1890418447314997737L;

    private GrupoPK grupoPk;

    private UsuarioPK usuarioPK;

    public GrupoPK getGrupoPk() {
        return grupoPk;
    }

    public void setGrupoPk(GrupoPK grupoPk) {
        this.grupoPk = grupoPk;
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioGrupoInscritoPK that = (UsuarioGrupoInscritoPK) o;
        return Objects.equals(usuarioPK, that.usuarioPK) &&
                Objects.equals(grupoPk, that.grupoPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioPK, grupoPk);
    }
}