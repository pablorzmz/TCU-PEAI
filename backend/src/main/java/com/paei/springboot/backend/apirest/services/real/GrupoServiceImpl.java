package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IGrupoDao;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoServiceImpl implements IGrupoService {


    @Autowired
    private IGrupoDao iGrupoDao;

    /**
     * Metodo que permite ubicar un grupo por su id
     * @param grupoPK llave del grupo a buscar
     * @return El grupo encontrado o null de otra manera
     */
    @Override
    public Grupo findById(GrupoPK grupoPK) {
        return iGrupoDao.findById(grupoPK).orElse(null);
    }
}
