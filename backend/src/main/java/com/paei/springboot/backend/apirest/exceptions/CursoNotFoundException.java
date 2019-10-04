package com.paei.springboot.backend.apirest.exceptions;

public class CursoNotFoundException extends RuntimeException {
    public CursoNotFoundException(String nombre){
        // Al ser una extension de RuntimeException este será el mensaje de la excepción
        super("No se encuentra el curso con nombre: " + nombre);
    }
}
