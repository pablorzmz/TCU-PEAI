package com.paei.springboot.backend.apirest.exceptions;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(String idMaterial) {
        // Al ser una extension de RuntimeException este será el mensaje de la excepción
        super("No se encuentra el material: "+ idMaterial);
    }
}
