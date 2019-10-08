package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.ISubseccionMaterialDao;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionEvaluacion;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubseccionMaterialServiceImp implements ISubseccionMaterialService {

    @Autowired
    private ISubseccionMaterialDao iSubseccionMaterialDao;

    @Override
    public List<SubseccionMaterial> obtenerSubseccionsPorGrupoPK(GrupoPK grupoPK) {
        return iSubseccionMaterialDao.findSubseccionMaterialByGrupoPk(grupoPK);
    }
}
