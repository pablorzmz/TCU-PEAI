package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilPermiso;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilPermisoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Perfil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IInstitucionPerfilPermisoDao extends CrudRepository<InstitucionPerfilPermiso, InstitucionPerfilPermisoPK> {

    @Query("SELECT U FROM InstitucionPerfilPermiso U WHERE U.institucionPerfilPermisoPK.perfil = ?1 AND U.institucionPerfilPermisoPK.institucion = ?2")
    public List<InstitucionPerfilPermiso> findInstitucionPerfilPermisoByInstitucionAndPerfil(Perfil perfil, Institucion institucion);

}
