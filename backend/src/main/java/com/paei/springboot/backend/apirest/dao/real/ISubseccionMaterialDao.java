package com.paei.springboot.backend.apirest.dao.real;

import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;
import com.paei.springboot.backend.apirest.model.entity.real.SubSeccionMaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISubseccionMaterialDao extends CrudRepository<SubseccionMaterial, SubSeccionMaterialPK> {

    @Query("select sbm from SubseccionMaterial sbm where sbm.grupo.id = ?1")
    public List<SubseccionMaterial> findSubseccionMaterialByGrupoPk(GrupoPK grupoPK);
}
