package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICursoService {

    /**
     * Método para recuperar un curso por id
     * @param id Id del curso
     * @return el curso en el objeto optional o un objeto optional vacio
     */
    public Optional<Curso> findyId(Long id);

    /**
     * Método para pedir las todos los cursos en páginas
     * @param pageable Objeto de solicitud de pagina de donde se obtiene el número de página que quiero y la cantidad de items por página
     * @return Retorna un Page con los cursos que estan en la página solicitada y los datos de la paginación
     */
    public Page<Curso> findAll(Pageable pageable);

    /**
     * Metodo que consulta los cursos asociados a un area tematica
     * @param areaTematicaPK id del area tematica de la cual se obtienen los cursos
     * @return Retorna una lista con los cursos
     */
    @Query("SELECT C FROM area_tematica A Join A.cursos C WHERE A.id = ?1")
    public List<Curso> findCursosByAreaTematica(AreaTematicaPK areaTematicaPK);

    /**
     * Metodo que obtiene un curso a partir de su CursoPK
     * @param cursoPK es la PK del curso que se desa obtener
     * @return Retorna el curso al cual le pertenece la PK
     */
    Curso getCurso(Long cursoPK);

    /**
     * Método que permite verificar si el usuario es profesor que imparte o es estudiante
     * @param u El usuario valido
     * @param c El curso valido
     * @return Verdadero o falso según sea el caso
     */
    boolean usuarioImparteCurso(Usuario u, Curso c);

    /**
     * Metodo para insertar un curso en la base de datos
     * @param curso Curso que se desea insertar
     * @return Retorna el curso insertado
     */
    Curso save(Curso curso);
}
