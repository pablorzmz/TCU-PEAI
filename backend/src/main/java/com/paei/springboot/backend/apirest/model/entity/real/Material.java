package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "material")
public class Material implements Serializable {

    private static final long serialVersionUID = 2766807839901964384L;

    public Material(){}

    public Material(MaterialPK id, String descripcion, String tipo, String url) {
        Id = id;
        Descripcion = descripcion;
        Tipo = tipo;
        Url = url;
    }

    @EmbeddedId
    private MaterialPK Id;

    @Column( name = "descripcion")
    private String Descripcion;

    @Column( name = "tipo")
    private String Tipo;

    @Column( name = "url")
    private String Url;


    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "categoria_id", unique = false, nullable = true, referencedColumnName = "categoria_id"),
    })
    private Categoria categoria;


    @MapsId("subSeccionMaterialId")
    @JoinColumns({
            @JoinColumn(name="subseccion_material_id", referencedColumnName="subseccion_material_id"),
    })
    @ManyToOne
    private SubseccionMaterial subseccionMaterial;


    public MaterialPK getId() {
        return Id;
    }

    public void setId(MaterialPK id) {
        Id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
