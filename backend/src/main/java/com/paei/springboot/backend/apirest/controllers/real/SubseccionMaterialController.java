package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.SubseccionMaterialDataAccessException;
import com.paei.springboot.backend.apirest.exceptions.SubseccionMaterialNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.SubSeccionMaterialPK;
import com.paei.springboot.backend.apirest.model.entity.real.SubseccionMaterial;
import com.paei.springboot.backend.apirest.services.real.ISubseccionMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/subseccion_material")
public class SubseccionMaterialController {

    @Autowired
    private ISubseccionMaterialService iSubseccionMaterialService;

    /**
     * Método principal que permite eliminar una subseccion de materiales que no tiene materiales asociados
     * @param id Identificador entero de la subseccion de materiales
     * @return Un response entity con el mensaje o bien, un error en caso de excepcion
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarSubseccionMaterial(@RequestParam int id){
        // Creación del mapa para las respuestas
        Map<String,Object> response = new HashMap<>();
        // Se construye el id para buscar la subseccion material
        SubSeccionMaterialPK subSeccionMaterialPK  = new SubSeccionMaterialPK();
        subSeccionMaterialPK.setId(id);
        // Se obtiene la subseccion especificada por id
        SubseccionMaterial subseccionMaterial = iSubseccionMaterialService.findById(subSeccionMaterialPK);
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
                    throw new SubseccionMaterialDataAccessException("Ocurrió un error al intentar eliminar la subscción de material.");
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
}