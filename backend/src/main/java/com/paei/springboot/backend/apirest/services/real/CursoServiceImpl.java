package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService {
    @Autowired
    ICursoDao iCursoDao;

    /**
     * Método para pedir las todos los cursos en páginas
     * @param pageable Objeto de solicitud de pagina de donde se obtiene el número de página que quiero y la cantidad de items por página
     * @return Retorna un Page con los cursos que estan en la página solicitada y los datos de la paginación
     */
    @Override
    public Page<Curso> findAll(Pageable pageable) {
        return iCursoDao.findAll(pageable);
    }

    /**
     * Metodo que consulta los cursos asociados a un area tematica
     * @param areaTematicaPK id del area tematica de la cual se obtienen los cursos
     * @return Retorna una lista con los cursos
     */
    @Override
    public List<Curso> findCursosByAreaTematica(AreaTematicaPK areaTematicaPK) {
        return this.iCursoDao.findCursosByAreaTematica(areaTematicaPK);
    }
}
