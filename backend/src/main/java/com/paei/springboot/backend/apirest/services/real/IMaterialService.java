package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Material;

public interface IMaterialService {

    /**
     * Método que retorna un material según si id
     * @param idMaterial es el id del material
     * @param idSubSeccionMaterial es el id de la subseccion a la que pertenece
     * @return retorna el material que tiene ese id
     */
    Material getById(String idMaterial, Long idSubSeccionMaterial);
}
