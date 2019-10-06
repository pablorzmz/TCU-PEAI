package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ICursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoDao iCursoDao;

    @Override
    public Optional<Curso> findyId(CursoPK id) {
        return iCursoDao.findById(id);
    }
}

