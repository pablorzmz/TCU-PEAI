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


    /**
     * Excepción para manejar solicitar información errónea en la vista principal del curso
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(VistaPrincipalCursoNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> VistaPrincipalCursoNotFoundException(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para manejar la excepcion de no encontrar un curso
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> cursoNoEncontrado(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para manejar la excepcion de no encontrar un curso
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> usuarioNoEncontrado(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Excepcion para cuando se va a utilizar una subsección de material y no existe
     * @param ex Excepcion lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(SubseccionMaterialNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> SubseccionMaterialNotFoundException(Exception ex){
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Excepcion para cuando haya un conflicto de datos que no permita realizar una operación
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(SubseccionMaterialDataAccessException.class)
    public ResponseEntity<CustomErrorResponse> SubseccionMaterialDataAccessException(Exception ex){
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Metodo para manejar la excepcion de que ya se encuentra un grupo con ese id
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(GrupoExist.class)
    public ResponseEntity<CustomErrorResponse> grupoExiste(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    /**
     * Metodo para manejar la excepcion de no encontrar un grupo
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(GrupoNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> grupoNoExiste(Exception ex){
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para manejar la excepcion de que no existe un material
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(MaterialNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> materialNoExiste(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para manejar la excepcion de BadRequest
     * @param ex Excepción lanzada
     * @return Retorna un CustomErrorResponse con la información del error
     */
    @ExceptionHandler(SolicitudInvalidaException.class)
    public ResponseEntity<CustomErrorResponse> solicitudInvalida(Exception ex) {
        CustomErrorResponse errors = new CustomErrorResponse(LocalDateTime.now(),  HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
