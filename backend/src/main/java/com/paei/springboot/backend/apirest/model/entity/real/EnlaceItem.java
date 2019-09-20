package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "enlace_item")
public class EnlaceItem implements Serializable {
    private static final long serialVersionUID = -9153459702575319845L;

    public EnlaceItem(){}

    public EnlaceItem(EnlaceItemPK id, String url, String tipo, String nombre) {
        Id = id;
        Url = url;
        Tipo = tipo;
        Nombre = nombre;
    }

    @EmbeddedId
    private EnlaceItemPK Id;

    @Column(name = "url")
    public String Url;

    @Column(name = "tipo")
    public String Tipo;

    @Column(name = "nombre")
    private String Nombre;

    public EnlaceItemPK getId() {
        return Id;
    }

    public void setId(EnlaceItemPK id) {
        Id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
