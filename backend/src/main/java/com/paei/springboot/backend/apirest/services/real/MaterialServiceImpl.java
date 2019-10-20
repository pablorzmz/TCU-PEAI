package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IMaterialDao;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;

@Service
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private IMaterialDao iMaterialDao;

    @Override
    public List<Material> obtenerMaterialesDeSubseccionMaterial(Long subseccionMaterialId) {
        return iMaterialDao.findAllBySubseccionMaterialId(subseccionMaterialId);
    }

    @Override
    public Material crearNuevoMaterial(Material material) {
        return iMaterialDao.save(material);
    }

    @Override
    public Material findMaterialById(MaterialPK materialPK) {
        return iMaterialDao.findById(materialPK).orElse(null);
    }

    @Override
    public void eliminarMaterialPorId(MaterialPK materialPK) {
        iMaterialDao.deleteById(materialPK);
    }

    /**
     * Método que retorna un material según si id
     * @param idMaterial es el id del material
     * @return retorna el material que tiene ese id
     */
    @Override
    public Material getById(String idMaterial, Long idSubSeccionMaterial){
        MaterialPK materialPK = new MaterialPK(idMaterial, idSubSeccionMaterial);
        return iMaterialDao.findById(materialPK).orElse(null);
    };

}
