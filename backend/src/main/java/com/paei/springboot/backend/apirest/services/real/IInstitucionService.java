package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;

public interface IInstitucionService {

    Institucion getInstitucion(InstitucionPK institucionPK);

}
