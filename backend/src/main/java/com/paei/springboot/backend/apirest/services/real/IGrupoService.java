package com.paei.springboot.backend.apirest.services.real;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.GrupoPK;

import java.util.List;

public interface IGrupoService {
    /**
     * Metodo que permite ubicar un grupo por su id
     * @param grupoPK llave del grupo a buscar
     * @return El grupo encontrado o null de otra manera
     */
    public Grupo findById(GrupoPK grupoPK);

    /**
     * Método que obtiene los grupos de un curso dado
     * @param cursoPK es la PK del grupo del que se quiere obtener los grupos
     * @return lista de grupos
     */
    List<Grupo> getGruposCurso(Long cursoPK);


    /**
     * Método que liga un grupo con un curso dado
     * @param grupo Grupo que se quiere guardar
     * @return retorna verdadero si la inserción es exitosa
     */
    Grupo setGrupoCurso(Grupo grupo);
}

