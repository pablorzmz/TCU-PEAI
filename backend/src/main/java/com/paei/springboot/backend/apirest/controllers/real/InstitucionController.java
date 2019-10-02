package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.services.real.IInstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/instituciones")
public class InstitucionController {

    @Autowired
    private IInstitucionService iInstitucionService;

    /**
     * Metodo para pedir las TODAS las intituciones en paginas
     * @param page nuemero de pagina que se quiere consultar
     * @return Retorna un Page con las intituciones que estan en la pagina solicitada y los datos de la paginacion
     */
    @GetMapping("obtener_instituciones/page/{page}")
    public Page<Institucion> index(@PathVariable Integer page){
        return iInstitucionService.findAll(PageRequest.of(page, 4));
    }
}
