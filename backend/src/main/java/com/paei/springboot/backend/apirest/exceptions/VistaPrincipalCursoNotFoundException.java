package com.paei.springboot.backend.apirest.exceptions;

public class VistaPrincipalCursoNotFoundException extends RuntimeException {
    public VistaPrincipalCursoNotFoundException(){
        super("La vista para el curso no se encuentra en el sistema.");
    }
}
