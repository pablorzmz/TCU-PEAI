package com.paei.springboot.backend.apirest.controllers.real;


import com.paei.springboot.backend.apirest.exceptions.VistaPrincipalCursoNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vista_principal_curso")
public class VistaPrincipalCursoController {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private ICursoService iCursoService;

    /**
     * Método que permite devolver un objeto entity con varias entidades para mostrar la
     * información del curso
     * @param id El el identificador del curso que viene del frontend
     * @return Recupera la información del curso, profesor, areatematica e institucion
     */
    @GetMapping("/informacion_curso_vista_principal")
    public ResponseEntity<?> recuperarVistaPrincipalCurso(@RequestParam Long id, @RequestParam String nombreUsuario){
        // Se intenta obtener el curso con ese id
        CursoPK idCurso = new CursoPK();
        idCurso.setId(id);
        // Se crea un map para meter las cosas en el response entity
        Map<String,Object> response = new HashMap<>();
        // Se recupera el curso
        Optional<Curso> curso = iCursoService.findyId(idCurso);
        // Si el curso es válido
        if (!curso.isEmpty()){
            // Se averigua si el usuario profesor o estudiante ver que profesor se recupera
            Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
            if (usuario != null){
                String profesorImparte = null;
                if (usuarioImparteCurso(usuario, curso.get())){
                    profesorImparte = usuario.getNombre() + " " + usuario.getApellidos();
                }else {
                    // Si no lo imparte es un estudiante y hay que buscar el profesor de su grupo
                    profesorImparte = obtenerGrupoUsuarioEstudiante(usuario, curso.get());
                }
                // Se colocan los datos para retornar al frontend
                response.put("profesorImparte",profesorImparte);
                response.put("curso",curso.get());
                response.put("nombreInstitucion",curso.get().getAreaTematica().getInstitucion().getInstitucionPK().getNombre());
                response.put("areaTematica",curso.get().getAreaTematica().getNombre());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                // Retornar la excepción personalizada
                throw new VistaPrincipalCursoNotFoundException();
            }
        }
        // Retornar la excepción personalizada
        throw new VistaPrincipalCursoNotFoundException();
    }

    /**
     * Método que permite recuperar el profesor del grupo al que pertecene el usuario estudiante
     * @param usuario Usuario actual que va a ver el curso
     * @param curso El curso como tal
     * @return El nombre del usuario profesor o nulo en caso de que no esté en ningún grupo.
     */
    private String obtenerGrupoUsuarioEstudiante(Usuario usuario, Curso curso) {
        // Se obtiene los grupos a los que pertenece el usuario
        var gruposUsuario = usuario.getUsuarioGrupoInscritos();
        System.out.println(gruposUsuario.toString());
        // Se itera sobre los grupos del usuario y se retorna el profesor de ese curso
        for (var gu: gruposUsuario){
            if (gu.getGrupo().getCurso().getId() == curso.getId()){
                return gu.getGrupo().getUsuario().getNombre() + " " + gu.getGrupo().getUsuario().getApellidos();
            }
        }
        return null;
    }

    /**
     * Método que permite verificar si el usuario es profesor que imparte o es estudiante
     * @param u El usuario valido
     * @param c El curso valido
     * @return Verdadero o falso según sea el caso
     */
    private boolean usuarioImparteCurso(Usuario u, Curso c) {
        // Se obtienen los grupos del Curso actual
        List<Grupo> grupos = c.getGrupos();
        // Se itera y se busca hasta hacer match para saber si el profesor que imparte
        for (var grupo: grupos) {
            if (grupo.getUsuario().getUsuarioPK().getNombreUsuario().equals(u.getUsuarioPK().getNombreUsuario())){
                return true;
            }
        }
        //  En caso contrario va a ser un estudiante
        return false;
    }

}
