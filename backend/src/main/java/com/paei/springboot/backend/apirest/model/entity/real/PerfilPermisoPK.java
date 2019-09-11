package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PerfilPermisoPK implements Serializable {
    private static final long serialVersionUID = -3046565457447423535L;

    public PerfilPermisoPK(Permiso permiso, Perfil perfil) {
        this.permiso = permiso;
        this.perfil = perfil;
    }

    private Permiso permiso;
    private Perfil perfil;

    @ManyToOne(cascade = CascadeType.ALL)
    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
