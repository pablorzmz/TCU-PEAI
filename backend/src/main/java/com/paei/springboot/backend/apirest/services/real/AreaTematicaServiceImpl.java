package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IAreaTematicaDao;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaTematicaServiceImpl implements IAreaTematicaService {

    @Autowired
    IAreaTematicaDao iAreaTematicaDao;

    /**
     * Método que retorna las áreas temáticas de una institución
     * @param institucionPK es la pk de la institución de la cuál se desean obtener las areas temáticas
     * @return retorna una lista de las áreas temáticas de la institución dada
     */
    @Override
    public List<AreaTematica> getAreaTematicaPorInstitucion(InstitucionPK institucionPK) {
        List<AreaTematica> listaAreasTematicas = iAreaTematicaDao.findAreaTematicaByInstitucion(institucionPK);
        return listaAreasTematicas;
    }

    /**
     * Método que retorna las areas tematicas existentes
     * @return retorna una lista de las áreas temáticas existentes
     */
    @Override
    public List<AreaTematica> getAreasTematicas(){
        List<AreaTematica> listaAreasTematicas = iAreaTematicaDao.findAll();
        return listaAreasTematicas;
    }

    /**
     * Metodo para obtener la informacion de un area tematica
     * @param areaTematicaPK id del area tematica de la cual quiero la informacion
     * @return retorna el area tematica solicitada
     */
    @Override
    public AreaTematica getAreaTematica(AreaTematicaPK areaTematicaPK) {
        try {
            Optional<AreaTematica> areaTematica = iAreaTematicaDao.findById(areaTematicaPK);
            return areaTematica.get();
        }catch (Exception e){
            return null;
        }
    }
}
