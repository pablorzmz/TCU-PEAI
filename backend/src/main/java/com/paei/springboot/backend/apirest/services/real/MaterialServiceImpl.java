package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IMaterialDao;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private IMaterialDao iMaterialDao;

    @Override
    public List<Material> obtenerMaterialesDeSubseccionMaterial(Long subseccionMaterialId) {
        return iMaterialDao.findAllBySubseccionMaterialId(subseccionMaterialId);
    }
}
