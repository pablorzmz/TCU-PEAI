package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;

import java.util.List;

public interface IUsuarioMaterialComentaService {

    /**
     * Método que agrega comentarios a un materia
     * @param usuarioMaterialComenta es el comentario a guardar
     * @return retorna el comentario guardado
     */
    UsuarioMaterialComenta agregarComentarioMaterial(UsuarioMaterialComenta usuarioMaterialComenta);

    /**
     * Encuentra todos los comentarios de un material según su pk, sin fecha
     * @param materialPK el pk del material
     * @return Lista de comentarios guardados
     */
    List<UsuarioMaterialComenta> findAllByMaterialPK(MaterialPK materialPK);
}
