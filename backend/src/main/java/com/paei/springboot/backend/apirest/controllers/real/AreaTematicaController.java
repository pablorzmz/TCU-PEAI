package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.InstitucionNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.AreaTematica;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.services.real.IAreaTematicaService;
import com.paei.springboot.backend.apirest.services.real.IInstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/areas_tematicas")

public class AreaTematicaController {

    @Autowired
    private IAreaTematicaService iAreaTematicaService;

    @Autowired
    private IInstitucionService iInstitucionService;

    /**
     * Metodo que devuelve las areas tematicas de una isntitución
     * @param nombre Representa el nombre de la institución
     * @return retorna la lista de areas tematicas que tenga la institución
     * @throws InstitucionNotFoundException si la institución no existe
     */
    @GetMapping("/listar_areas_tematicas_de_institucion")
    public List<AreaTematica> recuperarAreasTematicasDeInstitucion(@RequestParam String nombre){
        // Se crea la Pk de la institucion
        InstitucionPK institucionPK = new InstitucionPK(nombre);
        // Se busca la institucion
        Institucion institucion = iInstitucionService.getInstitucion(institucionPK);
        if(institucion != null){ // Si la institucion existe
            // Se busca por areaTematica
            List<AreaTematica> areaTematicas = iAreaTematicaService.getAreaTematicaPorInstitucion(institucionPK);
            return areaTematicas;
        }else {
            // Se retorna una excepcion si no se ecnuentra una isntitución
            throw new InstitucionNotFoundException(nombre);
        }
    }

    /**
     * Metodo que devuelve todas las areas tematicas del sistema
     * @return retorna la lista de areas tematicas
     */
    @GetMapping("/listar_areas_tematicas")
    public List<AreaTematica> recuperarAreasTematica(){
        List<AreaTematica> areaTematicas = iAreaTematicaService.getAreasTematicas();
        return areaTematicas;
    }
}
