package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.InstitucionNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.IInstitucionPerfilUsuarioService;
import com.paei.springboot.backend.apirest.services.real.IInstitucionService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioGrupoInscritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/ins_perf_usr")
public class InstitucionPerfilUsuarioController {

    @Autowired
    IInstitucionPerfilUsuarioService iInstitucionPerfilUsuarioService;

    @Autowired
    IInstitucionService institucionService;

    @Autowired
    IUsuarioGrupoInscritoService iUsuarioGrupoInscritoService;

    @GetMapping("estudiantes_de_institucion")
    public List<Usuario> estsDeInst(@RequestParam String nombreInst, @RequestParam Long idCurso){
        // Se crea la Pk de la institucion
        InstitucionPK institucionPK = new InstitucionPK(nombreInst);
        // Se busca la institucion
        Institucion institucion = institucionService.getInstitucion(institucionPK);
        if(institucion != null){ // Si la institucion existe
            // Se obtienen los datos
            List<Usuario> estudiantesDeInstitucion = iInstitucionPerfilUsuarioService.getEstudiantesDeInstitucion(institucionPK, "ROLE_Estudiante");
            List<Usuario> estudiantesDeCurso = iUsuarioGrupoInscritoService.estudiantesInscritosEnCurso(idCurso);
            for (Usuario est: estudiantesDeCurso) {
                if (estudiantesDeInstitucion.contains(est)) {
                    estudiantesDeInstitucion.remove(est);
                }
            }
            return estudiantesDeInstitucion;
        }else {
            // Se retorna una excepcion si no se ecnuentra una isntitución
            throw new InstitucionNotFoundException(nombreInst);
        }
    }
}
