package com.paei.springboot.backend.apirest.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "TABLA1_TABLA2_TABLA3")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKeyTabla123.tabla1",
                joinColumns = @JoinColumn(name = "TABLA1_ID")),
        @AssociationOverride(name = "primaryKeyTabla123.tabla2",
                joinColumns = @JoinColumn(name = "TABLA2_ID")),
        @AssociationOverride(name = "primaryKeyTabla123.tabla3",
                joinColumns = @JoinColumn(name = "TABLA3_ID"))
})
public class Tabla123 {
    // composite-id key
    private Tabla123Id primaryKeyTabla123 = new Tabla123Id();

    // additional fields
    private boolean activated;

    @EmbeddedId
    public Tabla123Id getPrimaryKeyTabla123() {
        return primaryKeyTabla123;
    }

    public void setPrimaryKeyTabla123(Tabla123Id primaryKeyTabla12) {
        this.primaryKeyTabla123 = primaryKeyTabla12;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Transient
    public Tabla1 getTabla1(){
        return primaryKeyTabla123.getTabla1();
    }

    @Transient
    public Tabla2 getTabla2(){
        return primaryKeyTabla123.getTabla2();
    }
    @Transient
    public Tabla3 getTabla3() {
        return primaryKeyTabla123.getTabla3();
    }

}
