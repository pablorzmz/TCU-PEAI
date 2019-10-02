package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionDao;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstitucionServiceImpl implements IInstitucionService {

    @Autowired
    private IInstitucionDao iInstitucionDao;

    /**
     * Método para pedir las TODAS las intituciones en páginas
     * @param pageable Objeto de solicitud de pagina de donde se obtiene el número de página que quiero y la cantidad de items por página
     * @return Retorna un Page con las intituciones que estan en la página solicitada y los datos de la paginación
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Institucion> findAll(Pageable pageable) {
        return iInstitucionDao.findAll(pageable);
    }

    /**
     * Método que busca una institucion por su PK
     * @param institucionPK es la PK de la institución
     * @return retorna una institución, si existe, null en caso contrario
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