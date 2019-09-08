package com.paei.springboot.backend.apirest.services.foo;

import com.paei.springboot.backend.apirest.dao.foo.ITabla3Dao;
import com.paei.springboot.backend.apirest.model.entity.foo.Tabla3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tabla3ServiceImpl  implements  ITabla3Service{

    @Autowired
    private ITabla3Dao iTabla3Dao;

    @Override
    @Transactional
    public Tabla3 save(Tabla3 tabla3) {
        return iTabla3Dao.save(tabla3);
    }
}
