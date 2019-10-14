package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComentaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioMaterialComentaDao extends JpaRepository<UsuarioMaterialComenta, UsuarioMaterialComentaPK> {

    /**
     * Consulta que devuelve los comentarios de un material
     * @param materialPK es la pk del material de interes
     * @return retorna la lista de comentarios de ese material ordenados por fecha
     */
    @Query("FROM UsuarioMaterialComenta UMC WHERE UMC.id.material = ?1 ORDER BY UMC.id.Fecha")
    List<UsuarioMaterialComenta> findAllByMaterialPK(MaterialPK materialPK);
}
