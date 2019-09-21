package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PermisoPK implements Serializable {

    private static final long serialVersionUID = 5990491766005704262L;

    public PermisoPK(Long id) {
        Id = id;
    }

    public PermisoPK(){}

    @Column(name = "permiso_id")
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
        PermisoPK permisoPK = (PermisoPK) o;
        return Objects.equals(Id, permisoPK.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
