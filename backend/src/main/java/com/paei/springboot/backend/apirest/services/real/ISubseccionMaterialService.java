package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.*;

import java.util.List;
import java.util.Optional;

public interface ISubseccionMaterialService {
    /**
     *  Método que permite recuperar todas las subsecciones de de material de curso por grupo
     * @param grupoPK
     * @return
     */
    public List<SubseccionMaterial> obtenerSubseccionesPorGrupoPK(GrupoPK grupoPK);

    /**
     * Metodo que permite obtener los materiales asociados con la subseccion de material especificada
     * @param subSeccionMaterialPK
     * @return Una lista con los materiales asociados
     */
    public List<Material> obtenerMaterialesSubSeccionMaterial(Long subSeccionMaterialPK);

    /**
     * Metodo que permite recuperar una subseccion por el id
     * @param subSeccionMaterialPK id de la subseccions
     * @return nulo o la subseccion según el caso
     */
    public SubseccionMaterial findById(Long subSeccionMaterialPK);


    /**
     * Metodo que permite eliminar la subsección
     * @param subSeccionMaterialPK id de la subsección material a eliminar
     */
    public void eliminarSubseccionMaterialPorId(Long subSeccionMaterialPK);

    /**
     * Metodo que permite crear una nueva subsccion de material
     * @param nuevaSBM La nueva entidad a crear
     * @return La entidad creada
     */
    SubseccionMaterial crearActualizarSubseccion(SubseccionMaterial nuevaSBM);
}
