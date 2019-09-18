package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, UsuarioPK> {

    @Query("select u from Usuario u where u.usuarioPK.NombreUsuario = ?1")
    public Usuario findByNombreUsuario(String nombreUsuario);

}
