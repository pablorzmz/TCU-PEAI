package com.paei.springboot.backend.apirest.model.entity.real;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InstitucionPK implements Serializable {
    private static final long serialVersionUID = 3872230053906647109L;

    public InstitucionPK(){}

    public InstitucionPK(String nombre) {
        Nombre = nombre;
    }

    @Column(name = "institucion_nombre")
    @Length(max = 60)
    private String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitucionPK that = (InstitucionPK) o;
        return Objects.equals(Nombre, that.Nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre);
    }
}
