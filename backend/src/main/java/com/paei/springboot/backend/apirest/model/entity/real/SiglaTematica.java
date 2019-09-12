package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sigla_tematica")
public class SiglaTematica implements Serializable {

    private static final long serialVersionUID = -883967794851832045L;

    public SiglaTematica(SiglaTematicaPK id, String sigla) {
        Id = id;
        Sigla = sigla;
    }

    @EmbeddedId
    private SiglaTematicaPK Id;

    @Column(name = "sigla")
    private String Sigla;

    @OneToMany(mappedBy = "siglaTematica")
    private List<AreaTematica> areaTematicas = new ArrayList<>();

    public SiglaTematicaPK getId() {
        return Id;
    }

    public void setId(SiglaTematicaPK id) {
        Id = id;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String sigla) {
        Sigla = sigla;
    }
}
