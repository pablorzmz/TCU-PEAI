package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SiglaTematicaPK implements Serializable {
    private static final long serialVersionUID = 218622441679582547L;

    public SiglaTematicaPK(){}

    @Column(name = "sigla_tematica_id")
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiglaTematicaPK that = (SiglaTematicaPK) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public SiglaTematicaPK(Long id) {
        Id = id;
    }
}
