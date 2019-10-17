package com.paei.springboot.backend.apirest.controllers.real;


import com.paei.springboot.backend.apirest.exceptions.CursoNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.MaterialNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.SolicitudInvalidaException;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;
import com.paei.springboot.backend.apirest.services.real.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private IUploadMaterialService iUploadMaterialService;

    @Autowired
    private IMaterialService iMaterialService;

    @Autowired
    private IGrupoService iGrupoService;

    @Autowired
    private ICursoService iCursoService;

    @Autowired
    private ISubseccionMaterialService iSubseccionMaterialService;

    /**
     * Método que permite recuperar todos lo materiales de un grupo por cada una de sus subsecciones de material
     * @param cursoId Id entero del curso*
     * @return Un response entity con los datos, o bien, las respecticas excepciones.
     */
    @GetMapping("/materiales_grupo")
    public ResponseEntity<?> obtenerMaterialesGrupoPorSubseccion( @RequestParam Long cursoId ) {
        // Map para la respuesta
        Map<String,Object> response = new HashMap<>();
        response.put("materiales", new ArrayList<Map<String,Object>>() );
        // Se verifica si el curso existe
        Optional<Curso> curso = iCursoService.findyId(cursoId);
        if ( ! curso.isEmpty() ) {
            // Se recupera cada grupo de ese curso
            List<Grupo> gruposCurso = iGrupoService.getGruposCurso(cursoId);
            // Por cada grupo, se recuperan las subsecciones de material
            for ( var g: gruposCurso ){
                List<SubseccionMaterial> subseccionesGrupo = iSubseccionMaterialService.obtenerSubseccionesPorGrupoPK(g.getId());
                // Ahora por cada subseccion se recupera sus materiales
                for ( var sbm: subseccionesGrupo ) {
                    // Se crea un map temporal para organizar mejor el response
                    Map<String,Object> tmpMap = new HashMap<>();
                    List<Material> materialesSubseccion = iMaterialService.obtenerMaterialesDeSubseccionMaterial(sbm.getId());
                    // Se agregan a la respuesta de acuerdo a la subsección que pertenecer
                    tmpMap.put("sbm_id", sbm.getId().toString());
                    tmpMap.put("sbm_material", materialesSubseccion);
                    // Se agrega al response comun
                    ( ( ArrayList< Map< String,Object > > ) response.get( "materiales" )).add( tmpMap );
                }
            }
            // Finalmente se retornan los datos
            return  new ResponseEntity<>( response, HttpStatus.OK );
        }else {
            // Se retorna una excepcion de que el curso con ese id no sirve
            throw new CursoNotFoundException(cursoId);
        }
    }

    /**
     * Método que permite recuperar un material especifico de una subseccion especifica
     * @param idMaterial Id del material
     * @param idSubSeccion Id de la subseccion a la que pertenece
     * @return El material solicitado o una excepcion de que no existe
     */
    @GetMapping("/obtener_material_subseccion")
    public Material obtenerMaterialSubseccion( @RequestParam String idMaterial, @RequestParam Long idSubSeccion) {
        Material material = iMaterialService.getById(idMaterial, idSubSeccion);
        if(material != null){
            // Si el material existe se retorna
            material.setUrl(iUploadMaterialService.getPath(material.getId().getNombre()).toString());
            return material;
        }
        else {
            // Si el material no existe se retorna una excecpcion
            throw new MaterialNotFoundException(idMaterial);
        }
    }

}
