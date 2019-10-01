package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import org.springframework.data.repository.CrudRepository;

public interface IInstitucionDao extends CrudRepository<Institucion, InstitucionPK> {
}
