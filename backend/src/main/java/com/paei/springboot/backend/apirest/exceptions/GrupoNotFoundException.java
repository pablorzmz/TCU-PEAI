package com.paei.springboot.backend.apirest.exceptions;

import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;

public class GrupoNotFoundException extends RuntimeException {
    public GrupoNotFoundException(GrupoPK grupoPK){
        // Al ser una extension de RuntimeException este será el mensaje de la excepción
        super("No se encuentra el grupo: " + grupoPK.getCurso() + " " + grupoPK.getNumero() + " - " + grupoPK.getPeriodoTiempo());
    }
}
