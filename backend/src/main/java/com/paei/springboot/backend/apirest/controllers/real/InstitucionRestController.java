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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class InstitucionRestController {

    @Autowired
    private IInstitucionService institucionService;

    @GetMapping("/instituciones")
    public List<Institucion> index(){
        return institucionService.findAll();
    }

    @GetMapping("/instituciones2")
    public List<Institucion> index2(){
        return institucionService.findAllInstituciones();
    }

    @GetMapping("/instituciones/page/{page}")
    public Page<Institucion> index(@PathVariable Integer page){
        return institucionService.findAll(PageRequest.of(page, 4));
    }

    @GetMapping("/instituciones/{id}")
    public ResponseEntity<?> show(@PathVariable InstitucionPK id){
        Institucion institucion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            institucion = institucionService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (institucion == null) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Institucion>(institucion, HttpStatus.OK);
    }
}
