package com.paei.springboot.backend.apirest.services;

import com.paei.springboot.backend.apirest.dao.ITabla2Dao;
import com.paei.springboot.backend.apirest.model.entity.Tabla2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tabla2ServiceImpl implements ITabla2Service {

    @Autowired
    private ITabla2Dao iTabla2Dao;

    @Override
    @Transactional
    public Tabla2 save(Tabla2 tabla2) {
        return iTabla2Dao.save(tabla2);
    }
}
