package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilPermiso;
import com.paei.springboot.backend.apirest.model.entity.real.Perfil;

import java.util.List;

public interface IInstitucionPerfilPermisoService {

    public List<InstitucionPerfilPermiso> findInstitucionPerfilPermisoByInstitucionAndPerfil(Perfil perfil, Institucion institucion);
}
