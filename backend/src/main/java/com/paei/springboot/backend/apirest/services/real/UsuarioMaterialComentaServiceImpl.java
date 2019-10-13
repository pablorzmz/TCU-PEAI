package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.dao.real.IUsuarioMaterialComentaDao;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioMaterialComentaServiceImpl implements IUsuarioMaterialComentaService {

    @Autowired
    IUsuarioMaterialComentaDao iUsuarioMaterialComentaDao;

    @Override
    public UsuarioMaterialComenta agregarComentarioMaterial(UsuarioMaterialComenta usuarioMaterialComenta) {
        return iUsuarioMaterialComentaDao.save(usuarioMaterialComenta);
    }

    @Override
    public List<UsuarioMaterialComenta> findAll(){
        return iUsuarioMaterialComentaDao.findAll();
    };
}
