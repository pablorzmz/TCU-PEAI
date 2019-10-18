package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilUsuario;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilUsuarioPK;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IInstitucionPerfilUsuarioDao extends CrudRepository<InstitucionPerfilUsuario, InstitucionPerfilUsuarioPK> {

    /**
     * Metodo para obtener los estudiantes asociados a una institucion
     * @param institucionPK id de la institucion
     * @param nombrePerfil nombre del perfil de estudiante
     * @return Retorna la lista de estudiantes
     */
    @Query("SELECT IPU.institucionPerfilUsuarioPK.usuario FROM InstitucionPerfilUsuario IPU WHERE IPU.institucionPerfilUsuarioPK.institucion.institucionPK = ?1 AND IPU.institucionPerfilUsuarioPK.perfil.Nombre = ?2")
    public List<Usuario> getEstudiantesDeInstitucion(InstitucionPK institucionPK, String nombrePerfil);
}
