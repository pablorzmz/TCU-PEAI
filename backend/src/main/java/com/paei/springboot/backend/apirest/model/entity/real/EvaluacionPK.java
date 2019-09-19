package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EvaluacionPK implements Serializable {

    private static final long serialVersionUID = 1801541372316102902L;

    public EvaluacionPK(){}

    public EvaluacionPK(Integer id, SubseccionEvaluacionPK subsuccionEvaluacion) {
        Id = id;
        this.subsuccionEvaluacion = subsuccionEvaluacion;
    }

    @Column(name = "evaluacion_id")
    private Integer Id;

    private SubseccionEvaluacionPK subsuccionEvaluacion;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public SubseccionEvaluacionPK getSubsuccionEvaluacion() {
        return subsuccionEvaluacion;
    }

    public void setSubsuccionEvaluacion(SubseccionEvaluacionPK subsuccionEvaluacion) {
        this.subsuccionEvaluacion = subsuccionEvaluacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluacionPK that = (EvaluacionPK) o;
        return Objects.equals(Id, that.Id) &&
                Objects.equals(subsuccionEvaluacion, that.subsuccionEvaluacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, subsuccionEvaluacion);
    }
}
