package com.paei.springboot.backend.apirest.controllers.real;


import com.paei.springboot.backend.apirest.exceptions.VistaPrincipalCursoNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import com.paei.springboot.backend.apirest.services.real.ISubseccionMaterialService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vista_principal_curso")
public class VistaPrincipalCursoController {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private ICursoService iCursoService;

    @Autowired
    private ISubseccionMaterialService iSubseccionMaterialService;

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
                boolean imparte = false;
                if (usuarioImparteCurso(usuario, curso.get())){
                    profesorImparte = usuario.getNombre() + " " + usuario.getApellidos();
                    imparte = true;
                }else {
                    // Si no lo imparte es un estudiante y hay que buscar el profesor de su grupo
                    profesorImparte = obtenerGrupoUsuarioEstudiante(usuario, curso.get());
                }
                // En caso de que el grupo por alguna razón no tenga un profesor encargado.
                if (profesorImparte == null ) {throw new VistaPrincipalCursoNotFoundException();}

                // Se colocan los datos para retornar al frontend
                response.put("profesorImparte",profesorImparte);
                response.put("nombreInstitucion",curso.get().getAreaTematica().getInstitucion().getInstitucionPK().getNombre());
                response.put("areaTematica",curso.get().getAreaTematica().getNombre());
                // Se limpian datos innecesarios curso
                curso.get().setAreaTematica(null);
                response.put("curso",curso.get());
                // Se agregan las subsecciones de material para este curso segun el caso
                response.putAll(obtenerSubseccionesMaterialPorGrupo(id,nombreUsuario,imparte));
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
     * Método que permite obtener todos las subsecciones de material por grupo de un curso.
     * @param id
     * @param nombreUsuario
     * @return
     */
    private Map<String,Object> obtenerSubseccionesMaterialPorGrupo(Long id, String nombreUsuario, boolean imparteCurso) {
        // Se crea un usuario para obtener el o los grupos según el caso
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
        // Se crea el curso para obtener los grupos correspondientes
        CursoPK cursoPK = new CursoPK(); cursoPK.setId(id);
        Curso curso = iCursoService.findyId(cursoPK).get();
        // Se crea un map para la respuesta
        Map<String, Object> response = new HashMap<>();
        // Se crea una lista de grupos para obtener la subseccion material
        List<Grupo> grupos = new ArrayList<>();
        // Se verifica si el usuario imparte el curso, o bien, si es un estudiante del curso
        if (imparteCurso) {
            // Se obtienen los grupos en los que el profesor imparte
            for (var g: curso.getGrupos())
                if (g.getUsuario().getUsuarioPK() == usuario.getUsuarioPK())
                    grupos.add(g);
        }else{
            // Se obtiene el grupo en el que el usuario está inscrito en ese curso.
            for (var ugi: usuario.getUsuarioGrupoInscritos())
                if (ugi.getGrupo().getCurso().getId() == curso.getId())
                    grupos.add(ugi.getGrupo());
        }
        // Por cada grupo es necesario recuperar la subseccion de materiales
        Map<String, Object> subListaSubseccionesMateriales = new HashMap<>();
        response.put("subseccionesMaterialPorGrupo", subListaSubseccionesMateriales);
        for (var g: grupos){
            // Se obtienen las subsecciones de materiales
            var sbmLista = iSubseccionMaterialService.obtenerSubseccionsPorGrupoPK(g.getId());
            // Se limpian se atributos innnecsario para frontend
            for (var sbm: sbmLista){
                sbm.getGrupo().setCurso(null);
                sbm.getGrupo().setUsuario(null);
            }
            // Se colocan en la respuesta
            ((Map<String, Object>)response.get("subseccionesMaterialPorGrupo")).put(g.getId().getNumero().toString(),sbmLista);
        }
        return response;
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
