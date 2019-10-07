package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoDao extends JpaRepository<Curso, CursoPK> {
}
