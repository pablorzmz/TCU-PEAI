package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.ISubseccionMaterialDao;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubseccionMaterialServiceImp implements ISubseccionMaterialService {

    @Autowired
    private ISubseccionMaterialDao iSubseccionMaterialDao;

    @Override
    public List<SubseccionMaterial> obtenerSubseccionesPorGrupoPK(GrupoPK grupoPK) {
        return iSubseccionMaterialDao.findSubseccionMaterialByGrupoPk(grupoPK);
    }

    @Override
    public List<Material> obtenerMaterialesSubSeccionMaterial(Long subSeccionMaterialPK) {
        return iSubseccionMaterialDao.obtenerMaterialesAsociados(subSeccionMaterialPK);
    }

    @Override
    public SubseccionMaterial findById(Long subSeccionMaterialPK) {
        return iSubseccionMaterialDao.findById(subSeccionMaterialPK).orElse(null);
    }

    @Override
    public void eliminarSubseccionMaterialPorId(Long subSeccionMaterialPK) {
        iSubseccionMaterialDao.deleteById(subSeccionMaterialPK);
    }

    @Override
    public SubseccionMaterial crearNuevaSubseccion(SubseccionMaterial nuevaSBM) {
        return iSubseccionMaterialDao.saveAndFlush(nuevaSBM);
    }

}
