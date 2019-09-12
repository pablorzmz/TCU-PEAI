package com.paei.springboot.backend.apirest.model.entity.real;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "usuario_grupo_inscrito")
public class UsuarioGrupoInscrito implements Serializable {

    private static final long serialVersionUID = 6438232388229978753L;

    public UsuarioGrupoInscrito(UsuarioGrupoInscritoPK id, Float notaFinal) {
        Id = id;
        NotaFinal = notaFinal;
    }

    @EmbeddedId
    private UsuarioGrupoInscritoPK Id;

    @Column( name = "nota_final")
    private Float NotaFinal;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_usuario", unique = true, nullable = false)
    private Usuario usuario;

    public UsuarioGrupoInscritoPK getId() {
        return Id;
    }

    public void setId(UsuarioGrupoInscritoPK id) {
        Id = id;
    }

    public Float getNotaFinal() {
        return NotaFinal;
    }

    public void setNotaFinal(Float notaFinal) {
        NotaFinal = notaFinal;
    }

}
