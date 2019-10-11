package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;

import java.util.List;

public interface IGrupoService {
    public Grupo findById(GrupoPK grupoPK);

    /**
     * Método que obtiene los grupos de un curso dado
     * @param cursoPK es la PK del grupo del que se quiere obtener los grupos
     * @return lista de grupos
     */
    List<Grupo> getGruposCurso(CursoPK cursoPK);
}

