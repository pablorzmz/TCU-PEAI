package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;

import java.util.List;

public interface IGrupoService {

    /**
     * Método que obtiene los grupos de un curso dado
     * @param cursoPK es la PK del grupo del que se quiere obtener los grupos
     * @return lista de grupos
     */
    List<Grupo> getGruposCurso(CursoPK cursoPK);

    /**
     * Metodo que retorna el grupo segun su grupoPK
     * @param grupoPK es la PK del grupo
     * @return Grupo si existe o null en caso contrario
     */
    Grupo getGrupo(GrupoPK grupoPK);

    /**
     * Método que liga un grupo con un curso dado
     * @param grupo Grupo que se quiere guardar
     * @return retorna verdadero si la inserción es exitosa
     */
    Grupo setGrupoCurso(Grupo grupo);
}
