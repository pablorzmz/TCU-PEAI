package com.paei.springboot.backend.apirest.model.entity.real;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrupoPK implements Serializable {

    private static final long serialVersionUID = -7280981508233899842L;

    public GrupoPK(){}

    public GrupoPK(Long cursoId, Integer numero, String periodoTiempo) {
        this.cursoId = cursoId;
        Numero = numero;
        PeriodoTiempo = periodoTiempo;
    }

    private Long cursoId;

    @Column( name = "numero")
    @Length( max = 2)
    private Integer Numero;

    @Column( name = "periodo_tiempo")
    private String PeriodoTiempo;

    public Long getCurso() {
        return cursoId;
    }

    public void setCurso(Long curso) {
        this.cursoId = curso;
    }

    public Integer getNumero() {
        return Numero;
    }

    public void setNumero(Integer numero) {
        Numero = numero;
    }

    public String getPeriodoTiempo() {
        return PeriodoTiempo;
    }

    public void setPeriodoTiempo(String periodoTiempo) {
        PeriodoTiempo = periodoTiempo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoPK grupoPK = (GrupoPK) o;
        return Objects.equals(cursoId, grupoPK.cursoId) &&
                Objects.equals(Numero, grupoPK.Numero) &&
                Objects.equals(PeriodoTiempo, grupoPK.PeriodoTiempo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursoId, Numero, PeriodoTiempo);
    }
}

