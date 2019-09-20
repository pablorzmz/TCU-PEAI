package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubseccionEvaluacionPK implements Serializable {

    private static final long serialVersionUID = -6672966718784425768L;

    public SubseccionEvaluacionPK(){}

    public SubseccionEvaluacionPK(Integer id) {
        Id = id;
    }

    @Column( name = "subseccion_evaluacion_id")
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubseccionEvaluacionPK that = (SubseccionEvaluacionPK) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
