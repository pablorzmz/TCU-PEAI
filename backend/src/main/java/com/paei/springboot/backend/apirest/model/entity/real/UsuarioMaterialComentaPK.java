package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class UsuarioMaterialComentaPK implements Serializable {

    private static final long serialVersionUID = -4922637785241075774L;

    public UsuarioMaterialComentaPK(){}

    private MaterialPK material;

    private UsuarioPK usuario;

    @Column( name = "fecha")
    private Date Fecha;

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

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
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
