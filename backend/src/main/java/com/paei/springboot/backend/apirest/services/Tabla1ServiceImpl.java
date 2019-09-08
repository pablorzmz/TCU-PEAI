package com.paei.springboot.backend.apirest.services;

import com.paei.springboot.backend.apirest.dao.ITabla1Dao;
import com.paei.springboot.backend.apirest.model.entity.Tabla1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Tabla1ServiceImpl implements ITabla1Service {

    @Autowired
    public ITabla1Dao iTabla1Dao;

    @Override
    @Transactional
    public Tabla1 save(Tabla1 tabla1) {
        return iTabla1Dao.save(tabla1);
    }

    @Override
    @Transactional
    public List<Tabla1> findAll() {
        return iTabla1Dao.findAll();
    }
}
