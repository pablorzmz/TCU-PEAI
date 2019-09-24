package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionDao;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitucionServiceImpl implements IInstitucionService{

    @Autowired
    IInstitucionDao iInstitucionDao;

    /**
     * Método que retorna las áreas temáticas de una institución
     * @param institucionPK es la pk de la institución de la cuál se desean obtener las areas temáticas
     * @return retorna una lista de las áreas temáticas de la institución dada
     */
    @Override
    public List<AreaTematica> getAreaTematicaPorInstitucion(InstitucionPK institucionPK) {
        List<AreaTematica> listaAreasTematicas = iInstitucionDao.findAreaTematicaByInstitucion(institucionPK);
        return listaAreasTematicas;
    }

    /**
     * Método que busca una institucion por su PK
     * @param institucionPK es la PK de la institución
     * @return retorna una institucón, si existe, null en caso contrario
     */
    @Override
    public Institucion getInstitucion(InstitucionPK institucionPK) {
        try {
            Optional<Institucion> institucion = iInstitucionDao.findById(institucionPK);
            return institucion.get();
        }catch (Exception e){
            return null;
        }
    }

}
