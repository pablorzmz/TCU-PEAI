package com.paei.springboot.backend.apirest.dao.foo;

import com.paei.springboot.backend.apirest.model.entity.foo.Tabla1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITabla1Dao extends JpaRepository <Tabla1, Long> {
}
