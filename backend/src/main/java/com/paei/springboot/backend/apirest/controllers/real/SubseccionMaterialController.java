package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.SubseccionMaterialDataAccessException;
import com.paei.springboot.backend.apirest.exceptions.SubseccionMaterialNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import com.paei.springboot.backend.apirest.services.real.ISubseccionMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/subseccion_material")
public class SubseccionMaterialController {

    @Autowired
    private ISubseccionMaterialService iSubseccionMaterialService;

    @Autowired
    private IGrupoService iGrupoService;

    /**
     * Método principal que permite eliminar una subseccion de materiales que no tiene materiales asociados
     * @param id Identificador entero de la subseccion de materiales
     * @return Un response entity con el mensaje o bien, un error en caso de excepcion
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarSubseccionMaterial(@RequestParam Long id){
        // Creación del mapa para las respuestas
        Map<String,Object> response = new HashMap<>();
        // Se construye el id para buscar la subseccion material
        // Se obtiene la subseccion especificada por id
        SubseccionMaterial subseccionMaterial = iSubseccionMaterialService.findById(id);
        // Si el id es válido se procede a ver si puede eliminar
        if ( subseccionMaterial != null ) {
            // Luego se verifica si la subsección no tiene ningún material asociado
            List<Material> materialesSubseccion = iSubseccionMaterialService.obtenerMaterialesSubSeccionMaterial(subseccionMaterial.getId());
            // Si es cero se procede, sino, se lanza excepción de que no es posible eliminar
            if ( materialesSubseccion.isEmpty() ) {
                // Se realiza la eliminación pero en caso de que falle se devuelve excepción
                try {
                    // Se elimina y se responde con un mensaje éxito
                    iSubseccionMaterialService.eliminarSubseccionMaterialPorId(subseccionMaterial.getId());
                    response.put("mensaje","¡Subsección eliminada con éxito!");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }catch (Exception ex){
                    // Se retorna la excepción de que no se pudo eliminar
                    throw new SubseccionMaterialDataAccessException("Ocurrió un error al intentar eliminar la subscción de material. ");
                }
            }else{
                // lanzar excepcion de que no es posible eliminar
                throw new SubseccionMaterialDataAccessException("No es posible eliminar la subsección " + subseccionMaterial.getNombre() + " debido a que tiene materiales asociados.");
            }
        }else{
            // retornar una excepcion de subseccion material no encontrada
            throw new SubseccionMaterialNotFoundException();
        }
    }

    /**
     * Metodo que permite crear una nueva subsección material
     * @param nuevaSBM Un objeto válido de subsección material que viene de frontend
     * @param result El resultando del binding a la hora de contruir ese nuevo objeto
     * @return Retorna excepciones o exito con la nueva instancia creada en DB
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearSubseccionMaterial(@Valid @RequestBody SubseccionMaterial nuevaSBM, BindingResult result) {
        // Primero se crea un puntero a la posible nueva subsección de material
        SubseccionMaterial subseccionMaterialNueva = null;
        // Se crea el map para adjuntar los datos de las respuestas
        Map<String,Object> response = new HashMap<>();
        // Se pregunta si tiene errores en el proceso de binding
        if ( result.hasErrors() ){
            // Se retorna una excepción personalizada
            response.put("error","Binding tiene errores a la hora de crear instancia");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        // En caso contrario se intenta crear una nueva subseccion de material
        try {
            // Se recupera el grupo para crearlo
            Grupo grupo =  iGrupoService.findById(nuevaSBM.getGrupo().getId());
            if (grupo != null){
                // Se le asigna el grupo correspondiente a la nueva subsección
                nuevaSBM.setGrupo(grupo);
                // Se crea y recupera la nueva subsección de material
                subseccionMaterialNueva = iSubseccionMaterialService.crearNuevaSubseccion(nuevaSBM);
            }else{
                //Se debe retornar una excepcion de que el grupo es inválido
                response.put("error","El grupo para crear la subsección no se encuentra");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch ( DataAccessException ex ) {
            // Lanzar excepcion personalizada
            response.put("error","Sucedió un error al intentar crear una nueva instancia de subsección material." + ex.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Se crea de manera correcta y se coloca un mensaje con la entidad
        subseccionMaterialNueva.getGrupo().setUsuario(null);
        subseccionMaterialNueva.getGrupo().setCurso(null);
        response.put("nuevaSBM",subseccionMaterialNueva);
        response.put("mensaje","¡Nueva subsección creada con éxito!");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}