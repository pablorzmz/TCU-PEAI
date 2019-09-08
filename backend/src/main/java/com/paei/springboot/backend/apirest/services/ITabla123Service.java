package com.paei.springboot.backend.apirest.services;

import com.paei.springboot.backend.apirest.model.entity.Tabla123;

import java.util.List;

public interface ITabla123Service {
    public List<Tabla123> findAll();
    public Tabla123 save( Tabla123 tabla123);
}
