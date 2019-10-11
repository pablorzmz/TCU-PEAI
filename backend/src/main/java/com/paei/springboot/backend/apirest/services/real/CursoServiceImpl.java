package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService {
    @Autowired
    ICursoDao iCursoDao;

    @Override
    /**
     * Método para recuperar un curso por id
     * @param id Id del curso
     * @return el curso en el objeto optional o un objeto optional vacio
     */
    public Optional<Curso> findyId(Long id) {
        return iCursoDao.findById(id);
    }

    /**
     * Método para pedir las todos los cursos en páginas
     * @param pageable Objeto de solicitud de pagina de donde se obtiene el número de página que quiero y la cantidad de items por página
     * @return Retorna un Page con los cursos que estan en la página solicitada y los datos de la paginación
     */
    @Override
    public Page<Curso> findAll(Pageable pageable) {
        return iCursoDao.findAll(pageable);
    }

    /**
     * Metodo que consulta los cursos asociados a un area tematica
     * @param areaTematicaPK id del area tematica de la cual se obtienen los cursos
     * @return Retorna una lista con los cursos
     */
    @Override
    public List<Curso> findCursosByAreaTematica(AreaTematicaPK areaTematicaPK) {
        return this.iCursoDao.findCursosByAreaTematica(areaTematicaPK);
    }

    /**
     * Metodo que obtiene un curso a partir de su CursoPK
     * @param cursoPK es la PK del curso que se desa obtener
     * @return Retorna el curso al cual le pertenece la PK
     */
    @Override
    public Curso getCurso(Long cursoPK){
        try{
            // Se intenta recuperar el curso con el PK que venga como parametro
            Optional<Curso> curso = iCursoDao.findById(cursoPK);
            // Se retorna el curso en caso de que exista
            return curso.get();
        }catch (Exception e){
            // Si el curso no exista entonces se retorna null
            return null;
        }
    }

    /**
     * Método que permite verificar si el usuario es profesor que imparte o es estudiante
     * @param u El usuario valido
     * @param c El curso valido
     * @return Verdadero o falso según sea el caso
     */
    @Override
    public boolean usuarioImparteCurso(Usuario u, Curso c) {
        // Se obtienen los grupos del Curso actual
        List<Grupo> grupos = c.getGrupos();
        // Se itera y se busca hasta hacer match para saber si el profesor que imparte
        for (var grupo: grupos) {
            if (grupo.getUsuario().getUsuarioPK().getNombreUsuario().equals(u.getUsuarioPK().getNombreUsuario())){
                return true;
            }
        }
        //  En caso contrario va a ser un estudiante
        return false;
    }
}

