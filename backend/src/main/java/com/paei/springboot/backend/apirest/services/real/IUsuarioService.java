package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioService {

    @Query("select u from Usuario u where u.usuarioPK.NombreUsuario = ?1")
    public Usuario findUsuarioByNombreUsuario(String nombreUsuario);

}
