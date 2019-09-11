package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "perfil_permiso")
@AssociationOverrides({
        @AssociationOverride(name = "perfilPermiso.perfil",
                joinColumns = @JoinColumn(name = "perfil_id")),
        @AssociationOverride(name = "perfilPermiso.permiso",
                joinColumns = @JoinColumn(name = "permiso_id")),
})
public class PerfilPermiso implements Serializable {

    private static final long serialVersionUID = 820258423750136177L;

    public PerfilPermiso(PerfilPermisoPK perfilPermiso) {
        this.perfilPermiso = perfilPermiso;
    }

    private PerfilPermisoPK perfilPermiso;

    @EmbeddedId
    public PerfilPermisoPK getPerfilPermiso() {
        return perfilPermiso;
    }

    public void setPerfilPermiso(PerfilPermisoPK perfilPermiso) {
        this.perfilPermiso = perfilPermiso;
    }

    @Transient
    public Permiso getPermiso() {
        return  perfilPermiso.getPermiso();
    }

    @Transient
    public Perfil getPerfil(){
        return perfilPermiso.getPerfil();
    }
}
