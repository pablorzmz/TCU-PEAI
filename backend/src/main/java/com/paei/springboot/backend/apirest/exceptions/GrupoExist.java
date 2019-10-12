package com.paei.springboot.backend.apirest.exceptions;

public class GrupoExist extends RuntimeException {
    public GrupoExist(String cursoNombre, int numero, String periodoTiempo){
        super("El grupo número "+ numero +" con el periodo de tiempo "+ periodoTiempo +" del curso "+ cursoNombre + " ya existe.");
    }
}
