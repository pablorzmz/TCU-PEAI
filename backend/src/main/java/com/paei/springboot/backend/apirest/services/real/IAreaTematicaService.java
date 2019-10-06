package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;

import java.util.List;

public interface IAreaTematicaService {

    /**
     * Método que retorna las áreas temáticas de una institución
     * @param institucionPK es la pk de la institución de la cuál se desean obtener las areas temáticas
     * @return retorna una lista de las áreas temáticas de la institución dada
     */
    List<AreaTematica> getAreaTematicaPorInstitucion(InstitucionPK institucionPK);

    /**
     * Método que retorna las areas tematicas existentes
     * @return retorna una lista de las áreas temáticas existentes
     */
    List<AreaTematica> getAreasTematicas();

    /**
     * Metodo para obtener la informacion de un area tematica
     * @param areaTematicaPK id del area tematica de la cual quiero la informacion
     * @return retorna el area tematica solicitada
     */
    AreaTematica getAreaTematica(AreaTematicaPK areaTematicaPK);
}
