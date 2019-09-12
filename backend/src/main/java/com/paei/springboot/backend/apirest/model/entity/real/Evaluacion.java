package com.paei.springboot.backend.apirest.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "evaluacion")
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = -4441741033649109829L;

    public Evaluacion(EvaluacionPK id, Date fechaInicio, Date fechaFin, String nombre, String descripcion, Float valor, Boolean habilitada) {
        Id = id;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        Nombre = nombre;
        Descripcion = descripcion;
        Valor = valor;
        Habilitada = habilitada;
    }

    @EmbeddedId
    private EvaluacionPK Id;

    @Column( name = "fecha_inicio")
    private Date FechaInicio;

    @Column( name = "fecha_fin" )
    private Date FechaFin;

    @Column( name =  "nombre")
    private String Nombre;

    @Column( name = "descripcion")
    private String Descripcion;

    @Column( name = "valor" )
    private Float Valor;

    @Column ( name = "habilitada")
    private Boolean Habilitada;

    @MapsId("subseccion_evaluacion_id")
    @JoinColumns({
            @JoinColumn(name="subseccion_evaluacion_id", referencedColumnName="subseccion_evaluacion_id"),
    })
    @ManyToOne
    private SubseccionEvaluacion subseccionEvaluacion;

    public EvaluacionPK getId() {
        return Id;
    }

    public void setId(EvaluacionPK id) {
        Id = id;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        FechaFin = fechaFin;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Float getValor() {
        return Valor;
    }

    public void setValor(Float valor) {
        Valor = valor;
    }

    public Boolean getHabilitada() {
        return Habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        Habilitada = habilitada;
    }
}
