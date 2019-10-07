package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICursoService {
    /**
     * Método para pedir las todos los cursos en páginas
     * @param pageable Objeto de solicitud de pagina de donde se obtiene el número de página que quiero y la cantidad de items por página
     * @return Retorna un Page con los cursos que estan en la página solicitada y los datos de la paginación
     */
    public Page<Curso> findAll(Pageable pageable);

    /**
     * Metodo que consulta los cursos asociados a un area tematica
     * @param areaTematicaPK id del area tematica de la cual se obtienen los cursos
     * @return Retorna una lista con los cursos
     */
    @Query("SELECT C FROM area_tematica A Join A.cursos C WHERE A.id = ?1")
    public List<Curso> findCursosByAreaTematica(AreaTematicaPK areaTematicaPK);

    /**
     * Metodo que obtiene un curso a partir de su CursoPK
     * @param cursoPK es la PK del curso que se desa obtener
     * @return Retorna el curso al cual le pertenece la PK
     */
    Curso getCurso(CursoPK cursoPK);

}
