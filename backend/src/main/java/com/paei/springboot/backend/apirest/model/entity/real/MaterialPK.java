package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialPK implements Serializable {

    private static final long serialVersionUID = 6018210033638588985L;

    public MaterialPK(){}

    public MaterialPK(String nombre, Long subSeccionMaterial) {
        Nombre = nombre;
        this.subSeccionMaterialId = subSeccionMaterial;
    }

    @Column( name = "material_id")
    private String Nombre;

    private Long subSeccionMaterialId;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Long getSubSeccionMaterialId() {
        return subSeccionMaterialId;
    }

    public void setSubSeccionMaterialId(Long subSeccionMaterialId) {
        this.subSeccionMaterialId = subSeccionMaterialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialPK that = (MaterialPK) o;
        return Objects.equals(Nombre, that.Nombre) &&
                Objects.equals(subSeccionMaterialId, that.subSeccionMaterialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre, subSeccionMaterialId);
    }
}
