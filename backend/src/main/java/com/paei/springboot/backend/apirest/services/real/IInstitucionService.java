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

    Institucion getInstitucion(InstitucionPK institucionPK);

}