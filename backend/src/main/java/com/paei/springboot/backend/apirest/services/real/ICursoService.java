package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICursoService {

    /**
     * Método para recuperar un curso por id
     * @param id Id del curso
     * @return el curso en el objeto optional o un objeto optional vacio
     */
    public Optional<Curso> findyId(CursoPK id);

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
}
