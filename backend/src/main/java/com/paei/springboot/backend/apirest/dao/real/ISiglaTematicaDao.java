package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.SiglaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.SiglaTematicaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISiglaTematicaDao extends JpaRepository<SiglaTematica, SiglaTematicaPK> {
}