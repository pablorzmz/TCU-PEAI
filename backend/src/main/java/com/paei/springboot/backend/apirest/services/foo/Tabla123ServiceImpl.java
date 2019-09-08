package com.paei.springboot.backend.apirest.services.foo;

import com.paei.springboot.backend.apirest.dao.foo.ITabla123Dao;
import com.paei.springboot.backend.apirest.model.entity.foo.Tabla123;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Tabla123ServiceImpl implements ITabla123Service {

    @Autowired
    private ITabla123Dao iTabla123Dao;

    @Override
    public List<Tabla123> findAll() {
        return iTabla123Dao.findAll();
    }

    @Override
    @Transactional
    public Tabla123 save(Tabla123 tabla123) {
        return iTabla123Dao.save(tabla123);
    }
}
