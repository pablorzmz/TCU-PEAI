package com.paei.springboot.backend.apirest.exceptions;

public class AreaTematicaNotFoundException extends RuntimeException{
    public AreaTematicaNotFoundException(){
        super("No se encuentra el area tematica seleccionada");
    }
}
