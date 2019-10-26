package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscritoPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IUsuarioMaterialComentaDao extends JpaRepository<UsuarioMaterialComenta, UsuarioGrupoInscritoPK> {

    @Transactional
    @Modifying
    @Query("delete from UsuarioMaterialComenta umc where umc.id.material = ?1")
    public void eliminarComentarioDeMaterial(MaterialPK materialPK);

    /**
     * Consulta que devuelve los comentarios de un material
     * @param materialPK es la pk del material de interes
     * @return retorna la lista de comentarios de ese material ordenados por fecha
     */
    @Query("FROM UsuarioMaterialComenta UMC WHERE UMC.id.material = ?1 and UMC.Visible = 1 ORDER BY UMC.id.Fecha ASC")
    List<UsuarioMaterialComenta> findAllByMaterialPK(MaterialPK materialPK);
}