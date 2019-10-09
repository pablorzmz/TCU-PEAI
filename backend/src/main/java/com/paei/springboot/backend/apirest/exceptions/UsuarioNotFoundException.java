package com.paei.springboot.backend.apirest.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException (String nombreUsuario){
        super("El usuario "+ nombreUsuario + " no existe");
    }
}
