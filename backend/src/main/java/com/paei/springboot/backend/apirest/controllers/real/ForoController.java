package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.MaterialNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.SolicitudInvalidaException;
import com.paei.springboot.backend.apirest.exceptions.UsuarioNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.Material;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.model.entity.real.UsuarioMaterialComenta;
import com.paei.springboot.backend.apirest.services.real.IMaterialService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioMaterialComentaService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/foro")
public class ForoController {

    @Autowired
    IUsuarioMaterialComentaService iUsuarioMaterialComentaService;

    @Autowired
    IUsuarioService iUsuarioService;

    @Autowired
    IMaterialService iMaterialService;

    /**
     * Metodo que se encarga de guardar un comentario
     * @param usuarioMaterialComenta es la entidad en la que se guarda el comentario
     * @return Retorna el comentario guardado
     */
    @PostMapping("/comentar_material")
    public ResponseEntity<?> agregarComentarioMaterial(@RequestBody UsuarioMaterialComenta usuarioMaterialComenta){
        // Se obtiene el usuario que comenta
        String nombreDeUsuario;
        try {
            nombreDeUsuario = usuarioMaterialComenta.getId().getUsuario().getNombreUsuario();
        } catch (Exception e) {
            throw new SolicitudInvalidaException();
        }
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreDeUsuario);

        if(usuario != null){
            // Si el usuario existe se le añade al objeto entrante
            usuarioMaterialComenta.setUsuario(usuario);
        }
        else {
            // Si el usuario no existe se retorna una excecpcion
            throw new UsuarioNotFoundException(nombreDeUsuario);
        }

        // Se obtiene el material
        String idMaterial;
        Long idSubSeccion;
        try{
            idMaterial = usuarioMaterialComenta.getId().getMaterial().getNombre();
            idSubSeccion =  usuarioMaterialComenta.getId().getMaterial().getSubSeccionMaterialId();
        } catch (Exception e) {
            throw new SolicitudInvalidaException();
        }
        Material material = iMaterialService.getById(idMaterial, idSubSeccion);

        if(material != null){
            // Si el material existe se le añade al objeto entrante
            usuarioMaterialComenta.setMaterial(material);
        }
        else {
            // Si el material no existe se retorna una excecpcion
            throw new MaterialNotFoundException(idMaterial);
        }

        // Le damos la fecha servidor al comentario
        usuarioMaterialComenta.getId().setFecha(new Date());

        // Se crea un map para meter las cosas en el response entity
        Map<String,Object> response = new HashMap<>();

        // Se agrega nombre y apellidos de usuario
        response.put("nombrePersona", usuario.getNombre() + " " + usuario.getApellidos());

        // Se guarda el comentario y se recibe el resultado
        UsuarioMaterialComenta usuarioMaterialComentaGuardado = iUsuarioMaterialComentaService.agregarComentarioMaterial(usuarioMaterialComenta);

        // Se inserta la respuesta en el response
        response.put("usuarioMaterialComenta", usuarioMaterialComentaGuardado);

        // Se retorna el comentario creado
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Método para obtener todos los comentarios de un material de una subsección
     * @param idMaterial es el id del material
     * @param idSubSeccion es el id de la subsección a la que partenece
     * @return retorna una lista con los comentarios que pertenezcan a la pk que se crea con los ids anteriores
     */
    @GetMapping("/obtener_comentarios")
    public ResponseEntity<?> obtenerComentariosMaterial(@RequestParam String idMaterial, @RequestParam Long idSubSeccion){
        // Se obtiene el material
        Material material = iMaterialService.getById(idMaterial, idSubSeccion);
        if(material != null){
            // Si el material existe se le añade al objeto entrante
            List<UsuarioMaterialComenta> usuarioMaterialComentaList = iUsuarioMaterialComentaService.findAllByMaterialPK(material.getId());

            // Se crea un arreglo con los comentarios hechos en forma de hasmap
            List<Map<String,Object>> listaMapComentarios = new ArrayList<>();

            // Se itera sobre cada elemento para hacer el array de hashmaps
            usuarioMaterialComentaList.forEach(usuarioMaterialComentaItem -> {

                Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(usuarioMaterialComentaItem.getId().getUsuario().getNombreUsuario());

                // Se crea un map para meter las cosas en el response entity
                Map<String,Object> map = new HashMap<>();

                // Se agrega nombre y apellidos de cada usuario
                map.put("nombrePersona", usuario.getNombre() + " " + usuario.getApellidos());

                // Se inserta cada comentario
                map.put("usuarioMaterialComenta", usuarioMaterialComentaItem);

                // Se agrega el map a la lista
                listaMapComentarios.add(map);

            });

            return new ResponseEntity<List<Map<String,Object>>>(listaMapComentarios, HttpStatus.OK);
        }else {
            // Si el material no existe se retorna una excecpcion
            throw new MaterialNotFoundException(idMaterial);
        }
    }
}
