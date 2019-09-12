package com.paei.springboot.backend.apirest.model.entity.real;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class CursoPK implements Serializable {

    private static final long serialVersionUID = 1331919158691105548L;


    @Column(name = "curso_nombre")
    private String Nombre;

    private AreaTematicaPK AreaTematicaPK;


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public AreaTematicaPK getAreaTematicaPK() {
        return AreaTematicaPK;
    }

    public void setAreaTematicaPK(AreaTematicaPK areaTematicaPK) {
        AreaTematicaPK = areaTematicaPK;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoPK cursoPK = (CursoPK) o;
        return Objects.equals(Nombre, cursoPK.Nombre) &&
                Objects.equals(AreaTematicaPK, cursoPK.AreaTematicaPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre, AreaTematicaPK );
    }
}
