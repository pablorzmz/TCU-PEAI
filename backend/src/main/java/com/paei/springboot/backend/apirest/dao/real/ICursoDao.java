package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICursoDao extends JpaRepository<Curso, Long> {

    /**
     * Metodo que consulta los cursos asociados a un area tematica
     * @param areaTematicaPK id del area tematica de la cual se obtienen los cursos
     * @return Retorna una lista con los cursos
     */
    @Query("SELECT C FROM AreaTematica A Join A.cursos C WHERE A.Id = ?1")
    public List<Curso> findCursosByAreaTematica (AreaTematicaPK areaTematicaPK);
}
