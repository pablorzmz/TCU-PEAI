package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialPK implements Serializable {

    private static final long serialVersionUID = 6018210033638588985L;

    public MaterialPK(){}

    public MaterialPK(String nombre, SubSeccionMaterialPK subSeccionMaterial) {
        Nombre = nombre;
        this.subSeccionMaterial = subSeccionMaterial;
    }

    @Column( name = "material_id")
    private String Nombre;

    private SubSeccionMaterialPK subSeccionMaterial;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public SubSeccionMaterialPK getSubSeccionMaterial() {
        return subSeccionMaterial;
    }

    public void setSubSeccionMaterial(SubSeccionMaterialPK subSeccionMaterial) {
        this.subSeccionMaterial = subSeccionMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialPK that = (MaterialPK) o;
        return Objects.equals(Nombre, that.Nombre) &&
                Objects.equals(subSeccionMaterial, that.subSeccionMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre, subSeccionMaterial);
    }
}
