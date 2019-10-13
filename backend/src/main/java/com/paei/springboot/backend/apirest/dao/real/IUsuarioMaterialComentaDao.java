package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComentaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioMaterialComentaDao extends JpaRepository<UsuarioMaterialComenta, UsuarioMaterialComentaPK> {
}
