package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import org.springframework.data.repository.CrudRepository;

public interface IGrupoDao extends CrudRepository<Grupo, GrupoPK> {
}
