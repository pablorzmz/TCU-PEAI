package com.paei.springboot.backend.apirest.model.entity.real;

import com.paei.springboot.backend.apirest.model.entity.foo.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table ( name = "usuario_material_comenta")
public class UsuarioMaterialComenta  implements Serializable {

    private static final long serialVersionUID = -4349637338469336272L;

    public UsuarioMaterialComenta(){}

    public UsuarioMaterialComenta(UsuarioMaterialComentaPK id, String textoComentario, Date fecha, Boolean visible) {
        Id = id;
        TextoComentario = textoComentario;
        Fecha = fecha;
        Visible = visible;
    }

    @EmbeddedId
    private UsuarioMaterialComentaPK Id;

    @Column( name = "texto_comentario")
    private String TextoComentario;

    @Column( name = "fecha")
    private Date Fecha;

    @Column ( name =  "visible")
    private Boolean Visible;


    @MapsId("material")
    @JoinColumns({
            @JoinColumn(name="material_id", referencedColumnName="material_id"),
            @JoinColumn(name="subseccion_material_id", referencedColumnName="subseccion_material_id"),
    })
    @ManyToOne
    private Material material;

    @MapsId("nombre_usuario")
    @JoinColumn(name="nombre_usuario", referencedColumnName="nombre_usuario")
    @ManyToOne
    private Usuario usuario;


    public UsuarioMaterialComentaPK getId() {
        return Id;
    }

    public void setId(UsuarioMaterialComentaPK id) {
        Id = id;
    }

    public String getTextoComentario() {
        return TextoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        TextoComentario = textoComentario;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Boolean getVisible() {
        return Visible;
    }

    public void setVisible(Boolean visible) {
        Visible = visible;
    }
}
