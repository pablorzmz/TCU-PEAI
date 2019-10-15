package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Material;

import java.util.List;

public interface IMaterialService {

    /**
     * Método del servicio que permite obtener por el id de la subsección.
     * @return
     */
    public List<Material> obtenerMaterialesDeSubseccionMaterial(Long subseccionMaterialId);
}
