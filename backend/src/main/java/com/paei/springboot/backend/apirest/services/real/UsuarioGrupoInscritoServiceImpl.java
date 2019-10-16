package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IUsuarioGrupoInscritoDao;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscrito;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioGrupoInscritoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioGrupoInscritoServiceImpl implements IUsuarioGrupoInscritoService {

    @Autowired
    IUsuarioGrupoInscritoDao iUsuarioGrupoInscritoDao;

    @Override
    public List<Usuario> estudiantesInscritosEnGrupo(GrupoPK grupoPK) {
        return iUsuarioGrupoInscritoDao.estudiantesInscritosEnGrupo(grupoPK);
    }

    @Override
    public UsuarioGrupoInscrito save(UsuarioGrupoInscrito usuarioGrupoInscrito) {
        return iUsuarioGrupoInscritoDao.save(usuarioGrupoInscrito);
    }

    @Override
    public void delete(UsuarioGrupoInscritoPK usuarioGrupoInscritoPK) {
        iUsuarioGrupoInscritoDao.deleteById(usuarioGrupoInscritoPK);
    }

    @Override
    public List<Usuario> estudiantesInscritosEnCurso(Long cursoId) {
        return iUsuarioGrupoInscritoDao.estudiantesInscritosEnCurso(cursoId);
    }


}
