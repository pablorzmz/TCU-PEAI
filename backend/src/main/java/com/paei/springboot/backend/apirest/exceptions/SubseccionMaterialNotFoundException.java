package com.paei.springboot.backend.apirest.exceptions;

public class SubseccionMaterialNotFoundException extends RuntimeException {
    public SubseccionMaterialNotFoundException(){
        super("La subsección de material solicita no existe en el sistema.");
    }
}
