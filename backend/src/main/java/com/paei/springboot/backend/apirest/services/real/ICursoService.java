package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ICursoService {
    public Optional<Curso> findyId(CursoPK id);
}
