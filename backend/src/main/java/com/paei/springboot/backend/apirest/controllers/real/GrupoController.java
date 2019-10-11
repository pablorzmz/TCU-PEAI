package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.CursoNotFoundException;
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
}
