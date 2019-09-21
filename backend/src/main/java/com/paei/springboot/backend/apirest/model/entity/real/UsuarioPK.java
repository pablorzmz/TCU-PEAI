package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioPK implements Serializable {

    private static final long serialVersionUID = 824662639680083833L;

    public UsuarioPK(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public  UsuarioPK(){

    }

    @Column(name = "nombre_usuario")
    private String NombreUsuario;

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPK usuarioPK = (UsuarioPK) o;
        return Objects.equals(NombreUsuario, usuarioPK.NombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NombreUsuario);
    }
}
