package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "perfil_usuario")
@AssociationOverrides({
        @AssociationOverride(name = "perfilUsuario.perfil",
                joinColumns = @JoinColumn(name = "perfil_id")),
        @AssociationOverride(name = "perfilUsuario.usuario",
                joinColumns = @JoinColumn(name = "nombre_usuario")),
})
public class PerfilUsuario implements Serializable {
    private static final long serialVersionUID = 3271167691415975836L;

    public PerfilUsuario(PerfilUsuarioPK perfilUsuario, Boolean activado) {
        this.perfilUsuario = perfilUsuario;
        Activado = activado;
    }

    private PerfilUsuarioPK perfilUsuario;

    @Column(name = "activado")
    private Boolean Activado;

    @EmbeddedId
    public PerfilUsuarioPK getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuarioPK) {
        this.perfilUsuario = perfilUsuario;
    }

    public Boolean getActivado() {
        return Activado;
    }

    public void setActivado(Boolean activado) {
        Activado = activado;
    }

    @Transient
    public Perfil getPerfil()
    {
        return this.perfilUsuario.getPerfil();
    }

    @Transient
    public Usuario getUsuario(){
        return this.perfilUsuario.getUsuario();
    }

}
