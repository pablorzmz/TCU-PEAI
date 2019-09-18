package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionPerfilPermisoDao;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilPermiso;
import com.paei.springboot.backend.apirest.model.entity.real.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitucionPerfilPermisoServiceImpl implements IInstitucionPerfilPermisoService {


    @Autowired
    private IInstitucionPerfilPermisoDao iInstitucionPerfilPermisoDao;

    @Override
    public List<InstitucionPerfilPermiso> findInstitucionPerfilPermisoByInstitucionAndPerfil(Perfil perfil, Institucion institucion){
        return iInstitucionPerfilPermisoDao.findInstitucionPerfilPermisoByInstitucionAndPerfil(perfil, institucion);
    }
}
