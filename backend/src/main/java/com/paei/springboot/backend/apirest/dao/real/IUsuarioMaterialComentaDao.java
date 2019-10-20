package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscritoPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IUsuarioMaterialComentaDao extends JpaRepository<UsuarioMaterialComenta, UsuarioGrupoInscritoPK> {

    @Transactional
    @Modifying
    @Query("delete from UsuarioMaterialComenta umc where umc.id.material = ?1")
    public void eliminarComentarioDeMaterial(MaterialPK materialPK);
}
