package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;

public interface IUsuarioMaterialComentaService {

    /**
     * Método que permite eliminar los comentarios asociados a un material
     * @param materialPK El material a eliminar
     */
    public void eliminarComentariosDeMaterial(MaterialPK materialPK);

}

