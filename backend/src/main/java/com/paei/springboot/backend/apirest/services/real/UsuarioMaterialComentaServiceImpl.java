package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IUsuarioMaterialComentaDao;
import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioMaterialComentaServiceImpl implements IUsuarioMaterialComentaService {

    @Autowired
    IUsuarioMaterialComentaDao iUsuarioMaterialComentaDao;

    /**
     * Método que agrega comentarios a un materia
     * @param usuarioMaterialComenta es el comentario a guardar
     * @return retorna el comentario guardado
     */
    @Override
    public UsuarioMaterialComenta agregarComentarioMaterial(UsuarioMaterialComenta usuarioMaterialComenta) {
        return iUsuarioMaterialComentaDao.save(usuarioMaterialComenta);
    }

    /**
     * Encuentra todos los comentarios de un material según su pk, sin fecha
     * @param materialPK el pk del material
     * @return Lista de comentarios guardados
     */
    @Override
    public List<UsuarioMaterialComenta> findAllByMaterialPK(MaterialPK materialPK){
        return iUsuarioMaterialComentaDao.findAllByMaterialPK(materialPK);
    };
}
