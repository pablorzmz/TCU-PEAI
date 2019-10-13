package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInstitucionService {

    /**
     * Método para pedir las TODAS las intituciones en páginas
     * @param pageable Objeto de solicitud de pagina de donde se obtiene el número de página que quiero y la cantidad de items por página
     * @return Retorna un Page con las intituciones que estan en la página solicitada y los datos de la paginación
     */
    public Page<Institucion> findAll(Pageable pageable);

    /**
     * Método para pedir las TODAS las intituciones
     * @return Retorna una lista con todas las instituciones
     */
    public List<Institucion> findAll();

    /**
     * Método que busca una institucion por su PK
     * @param institucionPK es la PK de la institución
     * @return retorna una institución, si existe, null en caso contrario
     */
    Institucion getInstitucion(InstitucionPK institucionPK);

}