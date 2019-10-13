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
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/comentar_material")
    UsuarioMaterialComenta agregarComentarioMaterial(@RequestBody UsuarioMaterialComenta usuarioMaterialComenta){
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

        // Se retorna el comentario creado
        return iUsuarioMaterialComentaService.agregarComentarioMaterial(usuarioMaterialComenta);
    }

}
