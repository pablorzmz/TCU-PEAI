package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "institucion_perfil_usuario")
@AssociationOverrides({
        @AssociationOverride(name = "institucionPerfilUsuarioPK.usuario",
                joinColumns = @JoinColumn(name = "nombre_usuario")),
        @AssociationOverride(name = "institucionPerfilUsuarioPK.perfil",
                joinColumns = @JoinColumn(name = "perfil_id")),
        @AssociationOverride(name = "institucionPerfilUsuarioPK.institucion",
                joinColumns = @JoinColumn(name = "institucion_nombre"))
})
public class InstitucionPerfilUsuario implements Serializable {

    private static final long serialVersionUID = 3365562036753010084L;

    private InstitucionPerfilUsuarioPK institucionPerfilUsuarioPK;

    @Transient
    public Usuario geUsuario(){
        return this.institucionPerfilUsuarioPK.getUsuario();
    }

    @Transient
    public Perfil getPerfil(){
        return this.institucionPerfilUsuarioPK.getPerfil();
    }

    @Transient
    public Institucion getInstitucion(){
        return this.institucionPerfilUsuarioPK.getInstitucion();
    }

    @EmbeddedId
    public InstitucionPerfilUsuarioPK getInstitucionPerfilUsuarioPK() {
        return institucionPerfilUsuarioPK;
    }

    public void setInstitucionPerfilUsuarioPK(InstitucionPerfilUsuarioPK institucionPerfilUsuarioPK) {
        this.institucionPerfilUsuarioPK = institucionPerfilUsuarioPK;
    }
}
