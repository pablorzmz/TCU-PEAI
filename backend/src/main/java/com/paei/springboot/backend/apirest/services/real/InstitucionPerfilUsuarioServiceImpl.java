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

    /**
     * Metodo para obtener los estudiantes asociados a una institucion
     * @param institucionPK id de la institucion
     * @param nombrePerfil nombre del perfil de estudiante
     * @return Retorna la lista de estudiantes
     */
    @Override
    public List<Usuario> getEstudiantesDeInstitucion(InstitucionPK institucionPK, String nombrePerfil) {
        return iInstitucionPerfilUsuarioDao.getEstudiantesDeInstitucion(institucionPK, nombrePerfil);
    }
}
