package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscrito;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscritoPK;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioGrupoInscritoService {
    @Query("SELECT UGI.usuario FROM UsuarioGrupoInscrito UGI WHERE UGI.usuarioGrupoInscritoPK.grupoPk = ?1")
    public List<Usuario> estudiantesInscritosEnGrupo(GrupoPK grupoPK);

    public UsuarioGrupoInscrito save(UsuarioGrupoInscrito usuarioGrupoInscrito);

    public void delete(UsuarioGrupoInscritoPK usuarioGrupoInscritoPK);

    @Query("SELECT UGI.usuario FROM UsuarioGrupoInscrito UGI WHERE UGI.grupo.Id.cursoId = ?1")
    public List<Usuario> estudiantesInscritosEnCurso(Long cursoId);
}
