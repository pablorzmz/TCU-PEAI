package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscritoPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioMaterialComentaDao extends JpaRepository<UsuarioMaterialComenta, UsuarioGrupoInscritoPK> {

    @Query("delete from UsuarioMaterialComenta as umc where umc.id.material = ?1")
    public void eliminarComentarioDeMaterial(MaterialPK materialPK);
}
