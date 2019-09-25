package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IInstitucionService {
    public List<Institucion> findAll();

    public Page<Institucion> findAll(Pageable pageable);

    public Institucion findById(InstitucionPK nombre);

    @Query("SELECT I FROM Institucion I")
    public List<Institucion> findAllInstituciones();
}
