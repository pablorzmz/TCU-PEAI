package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IInstitucionDao extends CrudRepository <Institucion, InstitucionPK> {

    /**
     * Consulta las áreas temáticas de una institución
     *
     * @author Jefferson Álvarez
     * @param institucionPK es la pk de la institución de la cuál se desean obtener las areas temáticas
     */
    @Query("SELECT AreaTematica FROM Institucion I join I.areaTematicas AreaTematica WHERE I.institucionPK = ?1")
    List<AreaTematica> findAreaTematicaByInstitucion(InstitucionPK institucionPK);

}
