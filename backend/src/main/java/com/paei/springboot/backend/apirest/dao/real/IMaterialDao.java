package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMaterialDao extends JpaRepository<Material, MaterialPK> {

    /**
     * Consulta que permite obtener todos los materiales por el id de una subsección.
     * @param id Valor lonn id de la subsección
     * @return Una lista de materiales llena o vacia
     */
    @Query("select m from Material m where m.id.subSeccionMaterialId = ?1")
    public List<Material> findAllBySubseccionMaterialId(Long id);

}
