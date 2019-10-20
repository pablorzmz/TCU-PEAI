package com.paei.springboot.backend.apirest.exceptions;

public class MaterialDataException extends RuntimeException {
    public MaterialDataException(String mensaje){
        super(mensaje);
    }
}
