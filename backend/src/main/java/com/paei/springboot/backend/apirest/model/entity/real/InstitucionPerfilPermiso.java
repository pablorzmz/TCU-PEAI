package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table ( name =  "institucion_perfil_permiso")
@AssociationOverrides({
        @AssociationOverride(name = "institucionPerfilPermisoPK.permiso",
                joinColumns = @JoinColumn(name = "permiso_id")),
        @AssociationOverride(name = "institucionPerfilPermisoPK.perfil",
                joinColumns = @JoinColumn(name = "perfil_id")),
        @AssociationOverride(name = "institucionPerfilPermisoPK.institucion",
                joinColumns = @JoinColumn(name = "institucion_nombre"))
})
public class InstitucionPerfilPermiso implements Serializable {

    private static final long serialVersionUID = 5406202234498992205L;

    private InstitucionPerfilPermisoPK institucionPerfilPermisoPK;

    @Transient
    public Institucion getInstitucion()
    {
        return this.institucionPerfilPermisoPK.getInstitucion();
    }

    @Transient
    public Perfil getPerfil(){
        return  this.institucionPerfilPermisoPK.getPerfil();
    }

    @Transient
    public Permiso getPermiso(){
        return  this.institucionPerfilPermisoPK.getPermiso();
    }

    @EmbeddedId
    public InstitucionPerfilPermisoPK getInstitucionPerfilPermisoPK() {
        return institucionPerfilPermisoPK;
    }

    public void setInstitucionPerfilPermisoPK(InstitucionPerfilPermisoPK institucionPerfilPermisoPK) {
        this.institucionPerfilPermisoPK = institucionPerfilPermisoPK;
    }
}
