package com.paei.springboot.backend.apirest.model.entity.real;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {

    private static final long serialVersionUID = -628956646940225121L;

    public Usuario(){

    }

    public Usuario(UsuarioPK nombreUsuario, @Email String correo, String nombre, String apellidos, Date fechaNacimiento, String salt, String sexo, Long telefono, String foto, Boolean habilitado) {
        usuarioPK = nombreUsuario;
        Correo = correo;
        Nombre = nombre;
        Apellidos = apellidos;
        FechaNacimiento = fechaNacimiento;
        Salt = salt;
        Sexo = sexo;
        Telefono = telefono;
        Foto = foto;
        Habilitado = habilitado;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "institucionPerfilUsuarioPK.usuario",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<InstitucionPerfilUsuario> institucionPerfilUsuarios = new HashSet<>();

    @EmbeddedId
    private UsuarioPK usuarioPK;

    @Column(name = "correo")
    @Email
    private String Correo;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "apellidos")
    private String Apellidos;

    @Column(name = "fecha_nacimiento")
    private Date FechaNacimiento;

    @Column(name = "salt")
    private String Salt;

    @Column(name = "sexo")
    private String Sexo;

    @Column(name = "telefono")
    private Long Telefono;

    @Column(name = "foto")
    private String Foto;

    @Column(name = "habilitado")
    private Boolean Habilitado;

    public Boolean getHabilitado() {
        return Habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        Habilitado = habilitado;
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public Long getTelefono() {
        return Telefono;
    }

    public void setTelefono(Long telefono) {
        Telefono = telefono;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UsuarioGrupoInscrito> usuarioGrupoInscritos = new ArrayList<>();

    public Set<InstitucionPerfilUsuario> getInstitucionPerfilUsuarios() {
        return institucionPerfilUsuarios;
    }

    public void setInstitucionPerfilUsuarios(Set<InstitucionPerfilUsuario> institucionPerfilUsuarios) {
        this.institucionPerfilUsuarios = institucionPerfilUsuarios;
    }

    public List<UsuarioGrupoInscrito> getUsuarioGrupoInscritos() {
        return usuarioGrupoInscritos;
    }

    public void setUsuarioGrupoInscritos(List<UsuarioGrupoInscrito> usuarioGrupoInscritos) {
        this.usuarioGrupoInscritos = usuarioGrupoInscritos;
    }

    /*
    @OneToMany(mappedBy = "usuarioGrupoInscritoPK.usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsuarioGrupoInscrito> usuarioGrupoInscritos = new HashSet<>();

    public Set<UsuarioGrupoInscrito> getUsuarioGrupoInscritos() {
        return usuarioGrupoInscritos;
    }

    public void setUsuarioGrupoInscritos(Set<UsuarioGrupoInscrito> usuarioGrupoInscritos) {
        this.usuarioGrupoInscritos = usuarioGrupoInscritos;
    }
    */

}
