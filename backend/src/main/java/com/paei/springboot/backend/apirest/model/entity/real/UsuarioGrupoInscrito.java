package com.paei.springboot.backend.apirest.model.entity.real;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario_grupo_inscrito")
/*
@AssociationOverrides({
        @AssociationOverride(name = "usuarioGrupoInscritoPK.usuario",
                joinColumns = @JoinColumn(name = "nombre_usuario")),
        @AssociationOverride(name = "usuarioGrupoInscritoPK.grupo",
                joinColumns = @JoinColumn(name = "numero")),
        @AssociationOverride(name = "usuarioGrupoInscritoPK.grupo",
                joinColumns = @JoinColumn(name = "curso_nombre")),
        @AssociationOverride(name = "usuarioGrupoInscritoPK.grupo",
                joinColumns = @JoinColumn(name = "periodo_tiempo")),
})
*/
public class UsuarioGrupoInscrito implements Serializable {

    private static final long serialVersionUID = 6438232388229978753L;

    public UsuarioGrupoInscrito(){}

    @EmbeddedId
    private UsuarioGrupoInscritoPK usuarioGrupoInscritoPK;

    @Column( name = "nota_final")
    private Float NotaFinal;

    @MapsId("grupoPk")
    @JoinColumns({
            @JoinColumn(name="curso_nombre", referencedColumnName="curso_nombre"),
            @JoinColumn(name="numero", referencedColumnName="numero"),
            @JoinColumn(name="periodo_tiempo", referencedColumnName="periodo_tiempo")
    })
    @ManyToOne
    private Grupo grupo;

    @MapsId("usuarioPk")
    @JoinColumns({
            @JoinColumn(name="nombre_usuario", referencedColumnName="nombre_usuario"),
    })
    @ManyToOne
    private Usuario usuario;

    public UsuarioGrupoInscritoPK getUsuarioGrupoInscritoPK() {
        return usuarioGrupoInscritoPK;
    }

    public void setUsuarioGrupoInscritoPK(UsuarioGrupoInscritoPK usuarioGrupoInscritoPK) {
        this.usuarioGrupoInscritoPK = usuarioGrupoInscritoPK;
    }

    public Float getNotaFinal() {
        return NotaFinal;
    }

    public void setNotaFinal(Float notaFinal) {
        NotaFinal = notaFinal;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
