package com.paei.springboot.backend.apirest.model.entity.real;

import com.paei.springboot.backend.apirest.model.entity.foo.Meeting;
import com.paei.springboot.backend.apirest.model.entity.foo.Tabla123;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {

    private static final long serialVersionUID = -628956646940225121L;

    @OneToMany(mappedBy = "perfilUsuario.usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PerfilUsuario> perfilUsuario = new HashSet<>();

    @EmbeddedId
    private UsuarioPK NombreUsuario;

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

    public UsuarioPK getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(UsuarioPK nombreUsuario) {
        NombreUsuario = nombreUsuario;
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

    public Set<PerfilUsuario> getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Set<PerfilUsuario> perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioGrupoInscrito> usuarioGrupoInscritos = new ArrayList<>();

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
