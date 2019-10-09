package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class CursoPK implements Serializable {

    private static final long serialVersionUID = 1331919158691105548L;

    public CursoPK(){}

    public CursoPK(Long id){
        this.Id = id;
    }

    @Column(name = "curso_id")
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
        CursoPK cursoPK = (CursoPK) o;
        return Objects.equals(Id, cursoPK.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
