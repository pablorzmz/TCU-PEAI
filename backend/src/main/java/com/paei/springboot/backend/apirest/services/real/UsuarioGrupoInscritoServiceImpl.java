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

    /**
     * Metodo para obtener los estudiantes inscritos en un grupo
     * @param grupoPK id del grupo
     * @return Retorna la lista de estudiantes inscritos
     */
    @Override
    public List<Usuario> estudiantesInscritosEnGrupo(GrupoPK grupoPK) {
        return iUsuarioGrupoInscritoDao.estudiantesInscritosEnGrupo(grupoPK);
    }

    /**
     * Metodo para insertar un estudiante en un grupo
     * @param usuarioGrupoInscrito usuario y grupo
     * @return Retorna el UsuarioGrupoInscrito creado
     */
    @Override
    public UsuarioGrupoInscrito save(UsuarioGrupoInscrito usuarioGrupoInscrito) {
        return iUsuarioGrupoInscritoDao.save(usuarioGrupoInscrito);
    }

    /**
     * Metodo para eliminar un usuario de un grupo
     * @param usuarioGrupoInscritoPK usuario y grupo
     */
    @Override
    public void delete(UsuarioGrupoInscritoPK usuarioGrupoInscritoPK) {
        iUsuarioGrupoInscritoDao.deleteById(usuarioGrupoInscritoPK);
    }

    /**
     * Metodo que retorna todos los estudiantes inscritos en algun grupo de un curso
     * @param cursoId id del curso
     * @return Retorna la lista de estudiantes
     */
    @Override
    public List<Usuario> estudiantesInscritosEnCurso(Long cursoId) {
        return iUsuarioGrupoInscritoDao.estudiantesInscritosEnCurso(cursoId);
    }


}
