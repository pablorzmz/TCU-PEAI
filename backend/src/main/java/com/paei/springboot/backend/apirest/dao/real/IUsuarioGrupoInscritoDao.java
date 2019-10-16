package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscrito;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscritoPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioGrupoInscritoDao extends CrudRepository<UsuarioGrupoInscrito, UsuarioGrupoInscritoPK> {

    @Query("SELECT UGI.usuario FROM UsuarioGrupoInscrito UGI WHERE UGI.usuarioGrupoInscritoPK.grupoPk = ?1")
    public List<Usuario> estudiantesInscritosEnGrupo(GrupoPK grupoPK);

    @Query("SELECT UGI.usuario FROM UsuarioGrupoInscrito UGI WHERE UGI.grupo.Id.cursoId = ?1")
    public List<Usuario> estudiantesInscritosEnCurso(Long cursoId);
}
