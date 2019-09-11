package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AreaTematicaPK implements Serializable {
    private static final long serialVersionUID = -6671460126658601266L;

    public AreaTematicaPK(Long id, InstitucionPK institucionId) {
        Id = id;
        InstitucionId = institucionId;
    }

    @Column(name = "area_tematica_id")
    private Long Id;

    private InstitucionPK InstitucionId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public InstitucionPK getInstitucionId() {
        return InstitucionId;
    }

    public void setInstitucionId(InstitucionPK institucionId) {
        InstitucionId = institucionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaTematicaPK that = (AreaTematicaPK) o;
        return Objects.equals(Id, that.Id) &&
                Objects.equals(InstitucionId, that.InstitucionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, InstitucionId);
    }
}
