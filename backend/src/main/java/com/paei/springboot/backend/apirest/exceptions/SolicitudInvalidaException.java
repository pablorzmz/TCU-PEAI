package com.paei.springboot.backend.apirest.exceptions;

public class SolicitudInvalidaException extends RuntimeException {
    public SolicitudInvalidaException() {
        // Al ser una extension de RuntimeException este será el mensaje de la excepción
        super("La solicitud no puede ser procesada debido a una solicitud incorrecta");
    }
}
