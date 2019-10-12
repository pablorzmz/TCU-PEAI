package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IGrupoDao;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * Método que obtiene los grupos de un curso dado
     * @param cursoPK es la PK del grupo del que se quiere obtener los grupos
     * @return lista de grupos
     */
    @Override
    public List<Grupo> getGruposCurso(Long cursoPK){
        return iGrupoDao.findGruposByCurso(cursoPK);
    }

    /**
     * Metodo que retorna el grupo segun su grupoPK
     * @param grupoPK es la PK del grupo
     * @return Grupo si existe o null en caso contrario
     */
    @Override
    public Grupo getGrupo(GrupoPK grupoPK){
        try {
            Optional<Grupo> grupo = iGrupoDao.findById(grupoPK);
            return grupo.get();
        }catch (Exception e){
            return null;
        }
    };

    /**
     * Método que liga un grupo con un curso dado
     * @param grupo Grupo que se quiere guardar
     * @return retorna verdadero si la inserción es exitosa
     */
    public Grupo setGrupoCurso(Grupo grupo){
        return iGrupoDao.save(grupo);
    }

}



