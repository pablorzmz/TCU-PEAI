package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.AreaTematicaNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematicaPK;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.services.real.IAreaTematicaService;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    ICursoService iCursoService;

    @Autowired
    IAreaTematicaService iAreaTematicaService;

    /**
     * Método para consultar todos los cursos que existen
     * @param page Numero de página que se quiere consultar
     * @return Retorna los cursos existentes en la pagina deseada
     */
    @GetMapping("listado_cursos/page/{page}")
    public Page<Curso> index(@PathVariable Integer page){
        return iCursoService.findAll(PageRequest.of(page, 4));
    }

    /**
     * Metodo para consultar los cursos asociados a un area tematica en particular
     * @param idArea Id del area tematica de la cual obtengo los cursos
     * @return Retorna los cursos pertenecientes a un area tematica en paginas
     */
    @GetMapping("cursos_por_area/{idArea}")
    public List<Curso> cursosPorArea(@PathVariable Long idArea){
        //Se crea un objeto de AreaTematicaPK
        AreaTematicaPK areaTematicaPK = new AreaTematicaPK(idArea);
        // Se busca el area tematica
        AreaTematica areaTematica = iAreaTematicaService.getAreaTematica(areaTematicaPK);
        if(areaTematica != null){
            //Se hace la consulta
            List<Curso> cursos = iCursoService.findCursosByAreaTematica(areaTematicaPK);
            // Se retornamos la lista de los cursos
            return cursos;
        }else {
            // Se retorna un error si no existe un area tematica con el Id proporcionado
            throw new AreaTematicaNotFoundException();
        }
    }
}
