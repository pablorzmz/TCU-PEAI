package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInstitucionService {

    public List<Institucion> findAll();

    public Page<Institucion> findAll(Pageable pageable);

    /**
     * Método que retorna las áreas temáticas de una institución
     * @param institucionPK es la pk de la institución de la cuál se desean obtener las areas temáticas
     * @return retorna una lista de las áreas temáticas de la institución dada
     */
    List<AreaTematica> getAreaTematicaPorInstitucion(InstitucionPK institucionPK);

    Institucion getInstitucion(InstitucionPK institucionPK);

}