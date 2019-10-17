package com.paei.springboot.backend.apirest.exceptions;

public class MaterialDataException extends RuntimeException {
    public MaterialDataException(){
        super("La información para crear el material no es correcta.");
    }
}
