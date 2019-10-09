package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.SubSeccionMaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISubseccionMaterialDao extends CrudRepository<SubseccionMaterial, SubSeccionMaterialPK> {

    @Query("select sbm from SubseccionMaterial sbm where sbm.grupo.id = ?1")
    public List<SubseccionMaterial> findSubseccionMaterialByGrupoPk(GrupoPK grupoPK);

    @Query("select sbm.materiales from SubseccionMaterial sbm where sbm.id = ?1")
    public List<Material> obtenerMaterialesAsociados(SubSeccionMaterialPK subSeccionMaterialPK);

}
