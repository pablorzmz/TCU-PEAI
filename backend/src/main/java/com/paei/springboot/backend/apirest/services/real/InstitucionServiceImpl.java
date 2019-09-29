package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionDao;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstitucionServiceImpl implements IInstitucionService{

    @Autowired
    private IInstitucionDao institucionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Institucion> findAll() {
        return (List<Institucion>)institucionDao.findAll();
    }

    /*
    @Override
    @Transactional(readOnly = true)
    public Page<Institucion> findAll(Pageable pageable) {
        return institucionDao.findAll(pageable);
    }*/

    @Override
    @Transactional(readOnly = true)
    public Institucion findById(InstitucionPK nombre) {
        return institucionDao.findById(nombre).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Institucion> findAllInstituciones() {
        return (List<Institucion>)institucionDao.findAllInstituciones();
    }
}
