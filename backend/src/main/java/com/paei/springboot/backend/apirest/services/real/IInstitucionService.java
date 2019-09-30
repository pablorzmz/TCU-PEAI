package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInstitucionService {
    public List<Institucion> findAll();

    public Page<Institucion> findAll(Pageable pageable);
}
