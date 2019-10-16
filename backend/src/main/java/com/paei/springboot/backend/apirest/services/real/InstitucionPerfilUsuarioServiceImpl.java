package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionPerfilUsuarioDao;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitucionPerfilUsuarioServiceImpl implements IInstitucionPerfilUsuarioService {
    @Autowired
    IInstitucionPerfilUsuarioDao iInstitucionPerfilUsuarioDao;

    @Override
    public List<Usuario> getEstudiantesDeInstitucion(InstitucionPK institucionPK, String nombrePerfil) {
        return iInstitucionPerfilUsuarioDao.getEstudiantesDeInstitucion(institucionPK, nombrePerfil);
    }
}
