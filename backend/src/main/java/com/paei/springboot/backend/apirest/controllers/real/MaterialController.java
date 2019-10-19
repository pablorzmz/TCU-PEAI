package com.paei.springboot.backend.apirest.controllers.real;


import com.paei.springboot.backend.apirest.exceptions.ArchivoMaterialIOException;
import com.paei.springboot.backend.apirest.exceptions.CursoNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.MaterialDataException;
import com.paei.springboot.backend.apirest.exceptions.SubseccionMaterialNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private IMaterialService iMaterialService;

    @Autowired
    private IGrupoService iGrupoService;

    @Autowired
    private ICursoService iCursoService;

    @Autowired
    private ISubseccionMaterialService iSubseccionMaterialService;

    @Autowired
    private IUploadMaterialService iUploadMaterialService;

    @Autowired IUsuarioMaterialComentaService iUsuarioMaterialComentaService;

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
     * Metodo que permite obtener del frontend un archivo  y una entidad de material para crearlo
     * @param archivo Archivo pdf que viene del frontend
     * @param nombreMaterial nombre del material
     * @param descripcion descripcion del material
     * @param sbmId id de la subsubsección de material
     * @return la entidad creada o la excepcion
     */
    @PostMapping("/materiales_grupo/upload")
    public ResponseEntity<?> crearMaterialSubseccion( @RequestParam("archivo") MultipartFile archivo ,
            @RequestParam("nombreMaterial") String nombreMaterial,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("sbmId") Long sbmId) {
        // de momento solo pdfs
        final String tipoArchivo = "Archivo PDF";
        // Map para la respuesta al frontend
        Map<String, Object> response =  new HashMap<>();
        // primero se obtiene la subsección de acuerdo con la información que viene del frontend
        SubseccionMaterial subseccionMaterial = iSubseccionMaterialService.findById(sbmId);
        if ( subseccionMaterial != null ) {
            // ahora se intenta guardar el archivo en el backend
            if ( !archivo.isEmpty() ){
                // para el nombre del archivo
                String nombreArchivo = null;
                // para almacenar el archivo
                try {
                    // se obtiene el nombre del arhivo que se almacenó
                    nombreArchivo = iUploadMaterialService.almacenar(archivo);
                }catch ( Exception ex ) {
                    // retornar una excepcion de que el archivo no se pudo almacenar
                    throw new ArchivoMaterialIOException();
                }
                // Para almacenar el material
                try {
                    // se almacena el nombre del archivo creado
                    Material nuevoMaterial = new Material();
                    MaterialPK nuevoMaterialPK = new MaterialPK(nombreMaterial, subseccionMaterial.getId());
                    // En caso de que el material ya existe se devuelve una excepción
                    if ( iMaterialService.findMaterialById(nuevoMaterialPK) != null ) {
                        // Se retorna una excepción
                        throw new MaterialDataException("El nombre del material a crear ya existe ene esta subsección.");
                    }
                    // Se establecen los antributo
                    nuevoMaterial.setUrl(nombreArchivo);
                    nuevoMaterial.setDescripcion(descripcion);
                    nuevoMaterial.setTipo(tipoArchivo);
                    nuevoMaterial.setId(nuevoMaterialPK);
                    nuevoMaterial.setSubseccionMaterial(subseccionMaterial);
                    // se crea el material
                    Material materialCreado = iMaterialService.crearNuevoMaterial(nuevoMaterial);
                    // se retorna el response de manera correcta
                    response.put("mensaje", "¡Material creado con éxito!");
                    response.put("material", materialCreado);
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }catch ( Exception ex ){
                    // retornar la excepcion de que no se pudo salvar el material
                    throw new MaterialDataException("La información para crear el material no es correcta.");
                }
            } else {
                // retornar una excepcion de que el archivo no se pudo almacenar
                throw new ArchivoMaterialIOException();
            }
        } else {
            // en caso de que sea nulo, se retorna una excepcion
            throw new SubseccionMaterialNotFoundException();
        }
    }

    @DeleteMapping("/materiales_grupo/eliminar")
    public ResponseEntity<?> eliminarMaterialSubseccion(@Valid @RequestBody MaterialPK materialPK, BindingResult result ){
        // Se crean el mapa para la respuesta
        Map<String,Object> response = new HashMap<>();
        // Primero se revisa si e
        if ( ! result.hasErrors() ) {
            // intenta eliminar el material y en caso de error, se muestra la excepcion
            Material material = iMaterialService.findMaterialById(materialPK);
            if (material == null ){
                throw new MaterialDataException("La información para eliminar el material no es correcta.");
            }
            // se intenta eliminar el archivo del material
            if ( !iUploadMaterialService.eliminar( material.getUrl()) ){
                throw new ArchivoMaterialIOException();
            }
            // Se eliminan comentarios y entidad
            try {
                // se eliminan todos los comentarios asociados a ese material
                iUsuarioMaterialComentaService.eliminarComentariosDeMaterial(material.getId());
                // Se elimina el material
                iMaterialService.eliminarMaterialPorId(material.getId());
                // se retorna el mensaje de que se elimina correctamente
                response.put("mensaje","¡Material eliminado con éxito!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }catch ( Exception e ){
                // lanzar la respectiva excepcion
                throw new MaterialDataException("No se pudo eliminar el material " + material.getId().getNombre());
            }
        }else{
            // retorna una excepcion de datos incorrectos
            throw new MaterialDataException("La información dada para eliminar el material es incorrecta.");
        }
    }
}
