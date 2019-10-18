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

    /**
     * Metodo para obtener los estudiantes de una institucion que no esten incritos en algun grupo de un curso
     * @param nombreInst Nombre de la institucion de la cual quiero sus estudiantes
     * @param idCurso Id del curso del cual omito sus estudiantes
     * @return Retorno la lista de estudiantes
     */
    @GetMapping("estudiantes_de_institucion")
    public List<Usuario> estsDeInst(@RequestParam String nombreInst, @RequestParam Long idCurso){
        // Se crea la Pk de la institucion
        InstitucionPK institucionPK = new InstitucionPK(nombreInst);
        // Se busca la institucion
        Institucion institucion = institucionService.getInstitucion(institucionPK);
        if(institucion != null){ // Si la institucion existe
            // Se obtienen los estudiantes de la institucion
            List<Usuario> estudiantesDeInstitucion = iInstitucionPerfilUsuarioService.getEstudiantesDeInstitucion(institucionPK, "ROLE_Estudiante");
            // Se obtienen los estudiantes del curso
            List<Usuario> estudiantesDeCurso = iUsuarioGrupoInscritoService.estudiantesInscritosEnCurso(idCurso);
            // Se eliminan los estudiantes que estan matriculados en algun grupo del curso
            for (Usuario est: estudiantesDeCurso) {
                if (estudiantesDeInstitucion.contains(est)) {
                    estudiantesDeInstitucion.remove(est);
                }
            }
            // Se retorna la lista de estudiantes
            return estudiantesDeInstitucion;
        }else {
            // Se retorna una excepcion si no se ecnuentra una institución
            throw new InstitucionNotFoundException(nombreInst);
        }
    }
}
