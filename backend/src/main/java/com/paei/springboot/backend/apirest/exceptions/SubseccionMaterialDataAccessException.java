package com.paei.springboot.backend.apirest.exceptions;

public class SubseccionMaterialDataAccessException extends  RuntimeException{
    public SubseccionMaterialDataAccessException (String mensaje){
        super(mensaje);
    }
}
