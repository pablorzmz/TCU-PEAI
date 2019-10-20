package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IUsuarioMaterialComentaDao;
import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMaterialComentaServiceImpl implements IUsuarioMaterialComentaService {

    @Autowired
    private IUsuarioMaterialComentaDao iUsuarioMaterialComentaDao;

    @Override
    public void eliminarComentariosDeMaterial(MaterialPK materialPK) {
        iUsuarioMaterialComentaDao.eliminarComentarioDeMaterial(materialPK);
    }
}
