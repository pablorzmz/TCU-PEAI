package com.paei.springboot.backend.apirest.services.foo;

import com.paei.springboot.backend.apirest.model.entity.foo.Tabla1;

import java.util.List;

public interface ITabla1Service {
    public Tabla1 save(Tabla1 tabla1);
    public List<Tabla1> findAll();
}
