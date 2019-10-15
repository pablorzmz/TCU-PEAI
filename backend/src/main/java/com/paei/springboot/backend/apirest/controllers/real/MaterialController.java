package com.paei.springboot.backend.apirest.controllers.real;


import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.services.real.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private IMaterialService iMaterialService;

    /**
     * Método que permite recuperar todos lo materiales de un grupo por cada una de sus subsecciones de material
     * @param grupo El grupo como tal para recuperar los materiales
     * @param result El resultado del binding para ver si el grupo es válido
     * @return Un response entity con los datos, o bien, las respecticas excepciones.
     */
    @GetMapping("/materiales_grupo")
    public ResponseEntity<?> obtenerMaterialesGrupoPorSubseccion(@Valid @RequestBody Grupo grupo,  BindingResult result){
        return null;
    }

}
