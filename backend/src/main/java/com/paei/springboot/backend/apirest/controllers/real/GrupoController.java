package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.CursoNotFoundException;
import com.paei.springboot.backend.apirest.exceptions.GrupoExist;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.exceptions.UsuarioNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    @Autowired
    private IGrupoService iGrupoService;

    @Autowired
    private ICursoService iCursoService;

    @Autowired
    private IUsuarioService iUsuarioService;

    private final int PERMISO_AGREGAR_GRUPO_CURSO = 6;


    @GetMapping("/listar_grupos_de_curso")
    public List<Grupo> recuperarGruposDeCurso(@RequestParam Long idCurso,  @RequestParam String nombreUsuario){
        // Se crea el cursoPK a partir del nombre recibido
        Long cursoPK = idCurso;
        // Se obtiene el curso a partir del cursoPK
        Curso curso = iCursoService.getCurso(cursoPK);
        // Si el curso no es null se obtienen los grupos y los retorna
        if(curso != null){
            // Se averigua si el usuario profesor o estudiante ver que profesor se recupera
            Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
            // Si el usuario existe
            List<Grupo> listaGrupos = curso.getGrupos();
            // Si el usuario existe
            if (usuario != null) {
                // Se verifica si es profe
                if(iCursoService.usuarioImparteCurso(usuario, curso)){
                    // Se crea una lista de grupos que imparte el profesor
                    List<Grupo> listaGruposImparte = new ArrayList<>();;
                    // Se verifica cuales grupos imparte
                    listaGrupos.forEach(grupo -> {
                        // Si imparte un grupo se añade a la lista
                        if(grupo.getUsuario().getUsuarioPK() == usuario.getUsuarioPK()){
                            listaGruposImparte.add(grupo);
                        }
                    });
                    return listaGruposImparte;
                }else {
                    // Se crea una lista de los grupos en los que está inscrito el usuario (aunque será solo 1)
                    List<Grupo> listaGruposInscrito = new ArrayList<>();;
                    // Se verifica cual es el grupo en el que esta inscrito dicho usuario
                    usuario.getUsuarioGrupoInscritos()
                            .forEach(usuarioGrupoInscrito -> {
                                // Si el grupo pertenece al curso, se retorna el añade el grupo
                                if (usuarioGrupoInscrito.getGrupo().getCurso() == curso){
                                    Grupo grupo = usuarioGrupoInscrito.getGrupo();
                                    listaGruposInscrito.add(grupo);
                                }
                            });
                    return listaGruposInscrito;
                }
            }else {
                // Si el usuario no existe se retorna la excepcion
                throw new UsuarioNotFoundException(nombreUsuario);
            }
        }else {
            // Si el curso es null es porque no existe, se retorna la excepcion
            throw new CursoNotFoundException(idCurso);
        }
    }

    @PostMapping("/crear_grupo_de_curso")
    public Grupo crearGrupoDeCurso(@RequestParam Long idCurso, @RequestParam Integer numero, @RequestParam String periodoTiempo, @RequestParam String nombreUsuario){
        // Se obtiene el curso a partir del id
        CursoPK cursoPK = new CursoPK(idCurso);
        Curso curso = iCursoService.getCurso(cursoPK);
        // Si el curso no existe se retorna una excepcion
        if (curso == null){
            throw new CursoNotFoundException(idCurso);
        }

        // Se obtiene el Usuario que impartirá el curso
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);
        // Si el usuario existe
        if (usuario != null){
            // AQUI SE VA A VALIDAR SI TIENE O NO EL PERMISO
            GrupoPK grupoPK = new GrupoPK(cursoPK, numero, periodoTiempo);

            // Si el grupo no existe, se crea
            if (iGrupoService.getGrupo(grupoPK) == null){
                Grupo grupo = new Grupo(grupoPK, usuario, curso);
                return iGrupoService.setGrupoCurso(grupo);
            }else {
                // Si el grupo ya  existe, se retorna una excepcion
                throw new GrupoExist(curso.getNombre(), numero, periodoTiempo);
            }

        }else {
            // Si el usuario no existe se retorna una excecpcion
            throw new UsuarioNotFoundException(nombreUsuario);
        }
    };
}
