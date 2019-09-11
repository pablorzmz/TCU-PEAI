package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class PerfilUsuarioPK implements Serializable {

    private static final long serialVersionUID = -1082986884029742944L;

    public PerfilUsuarioPK(Perfil perfil, Usuario usuario) {
        this.perfil = perfil;
        this.usuario = usuario;
    }

    private Perfil perfil;
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
