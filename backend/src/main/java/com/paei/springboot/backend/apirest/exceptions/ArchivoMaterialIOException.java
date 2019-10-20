package com.paei.springboot.backend.apirest.exceptions;

public class ArchivoMaterialIOException extends RuntimeException {
    public ArchivoMaterialIOException( String mensaje){
        super(mensaje);
    }
}
