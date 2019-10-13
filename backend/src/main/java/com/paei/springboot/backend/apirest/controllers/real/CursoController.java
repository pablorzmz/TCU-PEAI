package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.AreaTematicaNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.GrupoExist;
import com.paei.springboot.backend.apirest.exceptions.UsuarioNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.IAreaTematicaService;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    ICursoService iCursoService;

    @Autowired
    IAreaTematicaService iAreaTematicaService;

    @Autowired
    IGrupoService iGrupoService;

    @Autowired
    IUsuarioService iUsuarioService;

    /**
     * Método para consultar todos los cursos que existen
     * @param page Numero de página que se quiere consultar
     * @return Retorna los cursos existentes en la pagina deseada
     */
    @GetMapping("listado_cursos/page/{page}")
    public Page<Curso> index(@PathVariable Integer page){
        return iCursoService.findAll(PageRequest.of(page, 4));
    }

    /**
     * Metodo para consultar los cursos asociados a un area tematica en particular
     * @param idArea Id del area tematica de la cual obtengo los cursos
     * @return Retorna los cursos pertenecientes a un area tematica en paginas
     */
    @GetMapping("cursos_por_area/{idArea}")
    public List<Curso> cursosPorArea(@PathVariable Long idArea){
        //Se crea un objeto de AreaTematicaPK
        AreaTematicaPK areaTematicaPK = new AreaTematicaPK(idArea);
        // Se busca el area tematica
        AreaTematica areaTematica = iAreaTematicaService.getAreaTematica(areaTematicaPK);
        if(areaTematica != null){
            //Se hace la consulta
            List<Curso> cursos = iCursoService.findCursosByAreaTematica(areaTematicaPK);
            // Se retornamos la lista de los cursos
            return cursos;
        }else {
            // Se retorna un error si no existe un area tematica con el Id proporcionado
            throw new AreaTematicaNotFoundException();
        }
    }

    /**
     * Metodo para insertar un curso
     * @param curso datos del curso
     * @param idArea iD del area a la cual le quiero agregar el curso
     * @return Retorna el curso creado
     */
    @PostMapping("crear_curso")
    @ResponseStatus(HttpStatus.CREATED)
    public Curso create2(@RequestBody Curso curso, @RequestParam Long idArea, @RequestParam String nombreUsuario){
        //Se crea un objeto de AreaTematicaPK
        AreaTematicaPK areaTematicaPK = new AreaTematicaPK(idArea);
        // Se busca el area tematica
        AreaTematica areaTematica = iAreaTematicaService.getAreaTematica(areaTematicaPK);
        if(areaTematica != null){
            // Le agregamos al curso el Area Tematica
            curso.setAreaTematica(areaTematica);
            // Verificamos si el curso que se va a insertar tiene foto
            if(curso.getFoto() == null){
                // Si no tiene le agregamos la basica
                curso.setFoto("https://previews.123rf.com/images/krekdm/krekdm1608/krekdm160800078/61579579-volver-a-la-escuela-sin-fisuras-patr%C3%B3n-se-puede-utilizar-para-fondos-de-escritorio-fondo-de-p%C3%A1gina-web-pa.jpg");
            }
            // Se obtiene el Usuario que impartirá el curso
            Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
            // Si el usuario existe
            if (usuario != null){
                // AQUI SE VA A VALIDAR SI TIENE O NO EL PERMISO

                // Se inserta en la BD
                Curso cursoCreado =  iCursoService.save(curso);

                // Se crea el grupoPK
                GrupoPK grupoPK = new GrupoPK(cursoCreado.getId(), 1, "1 semestre");

                // Se crea el grupo
                Grupo grupo = new Grupo(grupoPK, usuario, cursoCreado);

                // Se agrega el grupo nuevo al curso
                iGrupoService.setGrupoCurso(grupo);

                // Se retorna el curso creado
                return cursoCreado;

            }else {
                // Si el usuario no existe se retorna una excecpcion
                throw new UsuarioNotFoundException(nombreUsuario);
            }
        }else {
            // Se retorna un error si no existe un area tematica con el Id proporcionado
            throw new AreaTematicaNotFoundException();
        }
    }
}
