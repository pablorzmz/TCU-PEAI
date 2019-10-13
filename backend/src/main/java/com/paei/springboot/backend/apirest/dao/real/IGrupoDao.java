package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IGrupoDao extends JpaRepository<Grupo, GrupoPK> {

    /**
     * Consulta que retorna una lista de grupos del curso dado
     * @param cursoPK es la PK del curso del que se quieren obtener los grupos
     * @return Retorna una lista de grupos de el curso dado
     */
    @Query("SELECT Grupo FROM Curso C join C.grupos Grupo WHERE C.id = ?1")
    List<Grupo> findGruposByCurso(Long cursoPK);

}
