package com.paei.springboot.backend.apirest.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /** Proporciona el manejo de excepciones en general */

    /**
     * Metodo para manejar la excepcion de no encontrar una institución
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(InstitucionNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> institucionNoEncontrada(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para el manejo de la excepcion de area tematica no encontrada
     * @param ex Excepción generada
     * @return Se retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(AreaTematicaNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> areaTematicaNoEncontrada(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
