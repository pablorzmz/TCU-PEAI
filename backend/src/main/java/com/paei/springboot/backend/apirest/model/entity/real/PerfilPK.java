package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PerfilPK implements Serializable {

    private static final long serialVersionUID = -66397918315088998L;

    public PerfilPK(Long id) {
        Id = id;
    }

    @Column(name = "perfil_id")
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
        PerfilPK perfilPK = (PerfilPK) o;
        return Objects.equals(Id, perfilPK.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
