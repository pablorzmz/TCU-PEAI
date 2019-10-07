package com.paei.springboot.backend.apirest.controllers.real;

import com.paei.springboot.backend.apirest.exceptions.CursoNotFoundException;
import com.paei.springboot.backend.apirest.model.entity.real.Curso;
import com.paei.springboot.backend.apirest.model.entity.real.CursoPK;
import com.paei.springboot.backend.apirest.model.entity.real.Grupo;
import com.paei.springboot.backend.apirest.services.real.ICursoService;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
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
    public boolean crearGrupoDeCurso(@RequestParam CursoPK idCurso, @RequestParam Long numeroGrupo, @RequestParam String periodoTiempo, @RequestParam String nombreUsuario)
}
