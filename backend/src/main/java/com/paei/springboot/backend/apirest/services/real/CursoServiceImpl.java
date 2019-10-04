package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoDao iCursoDao;

    /**
     * Metodo que obtiene un curso a partir de su CursoPK
     * @param cursoPK es la PK del curso que se desa obtener
     * @return Retorna el curso al cual le pertenece la PK
     */
    @Override
    public Curso getCurso(CursoPK cursoPK){
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
}
