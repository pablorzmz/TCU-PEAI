package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioMaterialComentaPK implements Serializable {

    private static final long serialVersionUID = -4922637785241075774L;

    private MaterialPK material;

    private UsuarioPK usuario;

    public MaterialPK getMaterial() {
        return material;
    }

    public void setMaterial(MaterialPK material) {
        this.material = material;
    }

    public UsuarioPK getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPK usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioMaterialComentaPK that = (UsuarioMaterialComentaPK) o;
        return Objects.equals(material, that.material) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, usuario);
    }
}
