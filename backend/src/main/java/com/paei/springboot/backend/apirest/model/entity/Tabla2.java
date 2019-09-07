package com.paei.springboot.backend.apirest.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TABLA2")
public class Tabla2 {
    private long id;
    private Set<Tabla123> tabla123s = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLA2_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "primaryKeyTabla123.tabla2",
            cascade = CascadeType.ALL)
    public Set<Tabla123> getTabla123s() {
        return tabla123s;
    }
    public void setTabla123s(Set<Tabla123> tabla123s) {
        this.tabla123s = tabla123s;
    }

    public void addTabla12(Tabla123 tabla123) {
        this.tabla123s.add(tabla123);
    }
}
