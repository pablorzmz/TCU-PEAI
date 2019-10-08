package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;

import java.util.List;

public interface ISubseccionMaterialService {
    /**
     *  Método que permite recuperar todas las subsecciones de de material de curso por grupo
     * @param grupoPK
     * @return
     */
    public List<SubseccionMaterial> obtenerSubseccionsPorGrupoPK(GrupoPK grupoPK);
}
