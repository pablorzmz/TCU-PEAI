package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.CursoNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Grupo> recuperarGruposDeCurso(@RequestParam Long idCurso){
        // Se crea el cursoPK a partir del nombre recibido
        CursoPK cursoPK = new CursoPK(idCurso);
        // Se obtiene el curso a partir del cursoPK
        Curso curso = iCursoService.getCurso(cursoPK);
        // Si el curso no es null se obtienen los grupos y los retorna
        if(curso != null){
            List<Grupo> listaGrupos = iGrupoService.getGruposCurso(cursoPK);
            return listaGrupos;
        }else {
            // Si el curso es null es porque no existe, se retorna la excepcion
            throw new CursoNotFoundException(idCurso);
        }
    }

    @GetMapping("/crear_grupo_de_curso")
    public Grupo crearGrupoDeCurso(@RequestParam CursoPK cursoPK, @RequestParam GrupoPK grupoPK, @RequestParam UsuarioPK usuarioPK){
        // Se obtiene el curso a partir del cursoPK
        Curso curso = iCursoService.getCurso(cursoPK);

        // Se obtiene el Usuario que imparte el curso
        String nombreUsuario = usuarioPK.getNombreUsuario();
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(nombreUsuario);

        Grupo grupo = new Grupo();
    };
}
