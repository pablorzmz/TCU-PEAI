package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.services.real.IInstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class InstitucionController {

    @Autowired
    private IInstitucionService institucionService;

    @GetMapping("/instituciones")
    public List<Institucion> index(){
        return institucionService.findAll();
    }

    @GetMapping("/instituciones/page/{page}")
    public Page<Institucion> index(@PathVariable Integer page){
        return institucionService.findAll(PageRequest.of(page, 4));
    }
}
