package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;

import java.util.List;

public interface IUsuarioMaterialComentaService {

    UsuarioMaterialComenta agregarComentarioMaterial(UsuarioMaterialComenta usuarioMaterialComenta);

    List<UsuarioMaterialComenta> findAll();
}
