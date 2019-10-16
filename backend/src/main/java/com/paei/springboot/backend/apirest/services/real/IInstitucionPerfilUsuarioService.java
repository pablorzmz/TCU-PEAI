package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IInstitucionPerfilUsuarioService {

    @Query("SELECT IPU.institucionPerfilUsuarioPK.usuario FROM InstitucionPerfilUsuario IPU WHERE IPU.institucionPerfilUsuarioPK.institucion.institucionPK = ?1 AND IPU.institucionPerfilUsuarioPK.perfil.Nombre = ?2")
    public List<Usuario> getEstudiantesDeInstitucion(InstitucionPK institucionPK, String nombrePerfil);
}
