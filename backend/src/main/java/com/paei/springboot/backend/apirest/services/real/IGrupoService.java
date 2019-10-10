package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;

public interface IGrupoService {
    public Grupo findById(GrupoPK grupoPK);
}
