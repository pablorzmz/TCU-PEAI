package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;

import java.util.List;

public interface IAreaTematicaService {

    /**
     * Método que retorna las áreas temáticas de una institución
     * @param institucionPK es la pk de la institución de la cuál se desean obtener las areas temáticas
     * @return retorna una lista de las áreas temáticas de la institución dada
     */
    List<AreaTematica> getAreaTematicaPorInstitucion(InstitucionPK institucionPK);

}
