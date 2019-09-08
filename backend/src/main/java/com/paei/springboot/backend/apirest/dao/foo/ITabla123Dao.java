package com.paei.springboot.backend.apirest.dao.foo;

import com.paei.springboot.backend.apirest.model.entity.foo.Tabla123;
import com.paei.springboot.backend.apirest.model.entity.foo.Tabla123Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITabla123Dao extends JpaRepository<Tabla123, Tabla123Id> {
}
