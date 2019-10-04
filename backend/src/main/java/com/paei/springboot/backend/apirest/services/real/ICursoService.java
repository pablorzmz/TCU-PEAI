package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;

public interface ICursoService {

    /**
     * Metodo que obtiene un curso a partir de su CursoPK
     * @param cursoPK es la PK del curso que se desa obtener
     * @return Retorna el curso al cual le pertenece la PK
     */
    Curso getCurso(CursoPK cursoPK);

}
