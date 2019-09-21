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

    public GrupoPK(CursoPK curso, Integer numero, String periodoTiempo) {
        this.curso = curso;
        Numero = numero;
        PeriodoTiempo = periodoTiempo;
    }

    private CursoPK curso;

    @Column( name = "numero")
    @Length( max = 2)
    private Integer Numero;

    @Column( name = "periodo_tiempo")
    private String PeriodoTiempo;

    public CursoPK getCurso() {
        return curso;
    }

    public void setCurso(CursoPK curso) {
        this.curso = curso;
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
        return Objects.equals(curso, grupoPK.curso) &&
                Objects.equals(Numero, grupoPK.Numero) &&
                Objects.equals(PeriodoTiempo, grupoPK.PeriodoTiempo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, Numero, PeriodoTiempo);
    }
}

