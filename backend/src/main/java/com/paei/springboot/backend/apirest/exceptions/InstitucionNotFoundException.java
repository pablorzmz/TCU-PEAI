package com.paei.springboot.backend.apirest.exceptions;

public class InstitucionNotFoundException extends RuntimeException {
    public InstitucionNotFoundException(String nombre) {
        // Al ser una extension de RuntimeException este será el mensaje de la excepción
        super("No se encuentra la institución con nombre: " + nombre);
    }
}