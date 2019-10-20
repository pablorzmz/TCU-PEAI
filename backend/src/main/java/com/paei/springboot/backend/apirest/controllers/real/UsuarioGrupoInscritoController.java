package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.GrupoNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.SubseccionMaterialDataAccessException;
import com.paei.springboot.backend.apirest.exceptions.UsuarioNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioGrupoInscritoService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("api/usr_grp_incs")
public class UsuarioGrupoInscritoController {
    @Autowired
    IUsuarioGrupoInscritoService iUsuarioGrupoInscritoService;

    @Autowired
    IGrupoService iGrupoService;

    @Autowired
    IUsuarioService iUsuarioService;

    /**
     * Metodo para listar todos los estudiantes inscritos en un grupo
     * @param numGrupo numero del grupo
     * @param cursoId id del curso al que pertenece el grupo
     * @param periodoT periodo de tiempo en el que se imparte el curso
     * @return Retorna una lista con los estudiantes
     */
    @GetMapping("estudiantes_de_grupo")
    public List<Usuario> estsDeGrupo(@RequestParam Integer numGrupo, @RequestParam Long cursoId, @RequestParam String periodoT){
        GrupoPK grupoPK = new GrupoPK(cursoId, numGrupo, periodoT);
        // Obtenemos el curso
        Grupo grupo = iGrupoService.findById(grupoPK);
        // Si el curso existe
        if (grupo != null){
            // Retornamos los estudiantes del grupo
            return iUsuarioGrupoInscritoService.estudiantesInscritosEnGrupo(grupoPK);
        }else{
            // En caso de no encontrar el grupo se tira un error
            throw new GrupoNotFoundException(grupoPK);
        }
    }

    /**
     * Metodo para inscribir un estudiante en un grupo
     * @param usuarioGrupoInscritoPK id del grupo al que se desea inscribir el usuario
     * @return Retorna el usuario inscrito en el curso
     */
    @PostMapping("insertar_estudiante_grupo")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario agregarEstudianteGrupo(@RequestBody UsuarioGrupoInscritoPK usuarioGrupoInscritoPK){
        // Hay que verificar si existe el usuario y el grupo
        // Buscamos el usuario
        String nombreUsuario = usuarioGrupoInscritoPK.getUsuarioPK().getNombreUsuario();
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
        if (usuario != null){
            // Buscamos el grupo
            Grupo grupo = iGrupoService.findById(usuarioGrupoInscritoPK.getGrupoPk());
            if (grupo != null){
                // Agregamos el usuario al grupo
                UsuarioGrupoInscrito usuarioGrupoInscrito = new UsuarioGrupoInscrito();
                usuarioGrupoInscrito.setUsuarioGrupoInscritoPK(usuarioGrupoInscritoPK);
                usuarioGrupoInscrito.setNotaFinal(0.0f);
                usuarioGrupoInscrito.setUsuario(usuario);
                usuarioGrupoInscrito.setGrupo(grupo);
                try {
                    // Agregamos el usuario al grupo
                    iUsuarioGrupoInscritoService.save(usuarioGrupoInscrito);
                    // Retornamos el usuario agregado
                    return usuarioGrupoInscrito.getUsuario();
                }catch (Exception ex){
                    // Retornamos error
                    throw new SubseccionMaterialDataAccessException("No se pudo insertar el estudiante");
                }
            }else{
                // En caso de no encontrar el grupo se tira un error
                throw new GrupoNotFoundException(grupo.getId());
            }
        }else{
            // En caso de no encontrar el usuario se tira un error
            throw new UsuarioNotFoundException(nombreUsuario);
        }
    }

    /**
     * Metodo para eliminar un estudiante de un grupo
     * @param numGrupo numero del grupo
     * @param idCurso id del curso al que pertenece el grupo
     * @param periodoT periodo de tiempo en el que se imparte el curso
     * @param nombreUsuario nombre del usuario que se desea eliminar del grupo
     * @return retorna el usuario eliminado
     */
    @DeleteMapping("eliminar_estudiante_grupo")
    public Usuario eliminarUsuarioGrupo(@RequestParam Long idCurso, @RequestParam Integer numGrupo, @RequestParam String periodoT, @RequestParam String nombreUsuario){
        // Hay que verificar si existe el usuario y el grupo
        // Buscamos el usuario
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
        if (usuario != null){
            GrupoPK grupoPK = new GrupoPK(idCurso, numGrupo, periodoT);
            // Buscamos el grupo
            Grupo grupo = iGrupoService.findById(grupoPK);
            if (grupo != null){
                UsuarioGrupoInscritoPK usuarioGrupoInscritoPK = new UsuarioGrupoInscritoPK(usuario.getUsuarioPK(), grupoPK);
                try {
                    // Eliminamos el usuario del grupo
                    iUsuarioGrupoInscritoService.delete(usuarioGrupoInscritoPK);
                    // Retornamos el usuario agregado
                    return usuario;
                }catch (Exception ex){
                    // Retornamos error
                    throw new SubseccionMaterialDataAccessException("No se pudo eliminar el estudiante");
                }
            }else{
                // En caso de no encontrar el grupo se tira un error
                throw new GrupoNotFoundException(grupoPK);
            }
        }else{
            // En caso de no encontrar el usuario se tira un error
            throw new UsuarioNotFoundException(nombreUsuario);
        }
    }
}
