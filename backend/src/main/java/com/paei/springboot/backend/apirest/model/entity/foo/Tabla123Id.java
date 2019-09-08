package com.paei.springboot.backend.apirest.model.entity.foo;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Tabla123Id implements Serializable {
    private Tabla1 tabla1;
    private Tabla2 tabla2;
    private Tabla3 tabla3;

    @ManyToOne(cascade = CascadeType.ALL)
    public Tabla1 getTabla1() {
        return tabla1;
    }

    public void setTabla1(Tabla1 tabla1) {
        this.tabla1 = tabla1;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Tabla2 getTabla2() {
        return tabla2;
    }

    public void setTabla2(Tabla2 tabla2) {
        this.tabla2 = tabla2;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Tabla3 getTabla3() {
        return tabla3;
    }

    public void setTabla3(Tabla3 tabla3) {
        this.tabla3 = tabla3;
    }
}
