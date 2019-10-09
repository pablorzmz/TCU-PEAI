package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IGrupoDao;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    private IGrupoDao iGrupoDao;


    /**
     * Método que obtiene los grupos de un curso dado
     * @param cursoPK es la PK del grupo del que se quiere obtener los grupos
     * @return lista de grupos
     */
    @Override
    public List<Grupo> getGruposCurso(CursoPK cursoPK){
        return iGrupoDao.findGruposByCurso(cursoPK);
    }

}