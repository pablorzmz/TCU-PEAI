package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IInstitucionDao extends JpaRepository<Institucion, InstitucionPK> {

    @Query("SELECT I FROM Institucion I")
    public List<Institucion> findAllInstituciones();
}