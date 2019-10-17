package com.paei.springboot.backend.apirest.exceptions;

public class ArchivoMaterialIOException extends RuntimeException {
    public ArchivoMaterialIOException(){
        super("Ocurrió un error a la hora de almacenar el archivo para el material");
    }
}
