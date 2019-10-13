package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.MaterialPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialDao extends JpaRepository<Material, MaterialPK> {
}
